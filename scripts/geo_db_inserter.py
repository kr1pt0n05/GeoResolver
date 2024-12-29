# This script connects to a PostgreSQL database and inserts geographical data into a table.
#
# 1. It first establishes a connection to a PostgreSQL database (`geoapi`) using the psycopg2 library.
# 2. It drops the existing 'locations' table if it exists and recreates it with the following columns:
#    - id: a serial primary key
#    - name: a string representing the location's name
#    - latitude: a float representing the geographical latitude
#    - longitude: a float representing the geographical longitude
#    - country_code: a string for the country's code (e.g., 'US', 'DE')
#    - modification_date: a date field representing when the data was last modified
#    - feature_code: a string representing the type of geographical feature (e.g., city, town)
# 3. The geographical data is parsed from a text file (`DE/DE.txt`) using the `geo_txt_parser` module.
#    - The parsed data is expected to be a list of objects, each containing attributes like name, latitude,
#      longitude, country_code, modification_date, and feature_code.
# 4. The script then iterates over the parsed data, inserting each entry's attributes into the 'locations' table.
# 5. After all data is inserted, the transaction is committed to the database.
# 6. If any errors occur during the database operations, they are caught and printed.
#
# This script is useful for loading geographical data from a text file into a PostgreSQL database.


import psycopg2
import geo_txt_parser


# Function to insert geographical data into a PostgreSQL database
def insert_geo_data(data, createIndex=False):
    try:

        with psycopg2.connect(user="postgres",
                              password="123456",
                              host="localhost",
                              port="5432",
                              database="geoapi") as conn:
            with conn.cursor() as cur:

                # Drop the table if it already exists
                cur.execute("DROP TABLE IF EXISTS locations")

                cur.execute("""
                    CREATE TABLE locations (
                        id SERIAL PRIMARY KEY, 
                        name VARCHAR(100), 
                        coordinates GEOGRAPHY(POINT, 4326),
                        country_code VARCHAR(5), 
                        modification_date DATE,
                        feature_code VARCHAR(5)
                    )
                """)

                if createIndex:
                    cur.execute("CREATE INDEX idx_coordinates ON locations USING GIST(coordinates)")

                # Insert data into the table
                for entry in data:
                    print(f"Inserting data for {entry.name}...")

                    cur.execute("""
                        INSERT INTO locations (name, coordinates, country_code, modification_date, feature_code) 
                        VALUES (%(name)s, ST_POINT(%(latitude)s, %(longitude)s, 4326), %(country_code)s, %(modification_date)s, %(feature_code)s)
                    """, entry.__dict__)

                # Commit the transaction
                conn.commit()
                print("Data inserted successfully!")

    except Exception as e:
        print(f"Error: {e}")


data = geo_txt_parser.parse_txt_file("../DE/DE.txt")

insert_geo_data(data, True)
