# This script defines a class and a function for parsing geographical data from a tab-separated .txt file and converting it into Entry objects.
#
# 1. **Entry Class**:
#    - The `Entry` class represents a single geographical entry with attributes such as:
#        - `geoname_id`: Unique identifier for the geographical location.
#        - `name`: Name of the location.
#        - `latitude`: Latitude of the location, converted to a float.
#        - `longitude`: Longitude of the location, converted to a float.
#        - `country_code`: Country code (e.g., 'US', 'DE').
#        - `modification_date`: Date when the data was last modified.
#        - `feature_code`: A code representing the type of feature (e.g., populated place, administrative division).
#    - The class has a `__str__` method that returns a human-readable string representation of the entry.
#
# 2. **parse_txt_file Function**:
#    - The `parse_txt_file` function reads a .txt file located at the given `path`.
#    - It reads the file line by line and splits each line by tab characters (`\t`), expecting the data to be tab-separated.
#    - It checks the `feature_code` (data[7]) to identify relevant entries that represent populated places or administrative divisions (e.g., "PPL", "PPLA").
#    - For each relevant line, an `Entry` object is created using the data from specific columns in the file and appended to the `entries` list.
#    - After reading the file, the function prints the total number of valid entries found and returns the list of `Entry` objects.
#
# This script is useful for converting a geographical data file into structured Python objects (Entry) for further processing or database insertion.


# Define the Entry class to represent geographical data
class Entry:
    def __init__(self, geoname_id, name, latitude, longitude, country_code, modification_date, feature_code):
        self.geoname_id = geoname_id
        self.name = name
        self.latitude = float(latitude)
        self.longitude = float(longitude)
        self.country_code = country_code
        self.modification_date = modification_date
        self.feature_code = feature_code

    def __str__(self):
        return f"Geoname ID: {self.geoname_id}, Name: {self.name}, Latitude: {self.latitude}, Longitude: {self.longitude}, Country Code: {self.country_code}, Modification Date: {self.modification_date}, Feature Code: {self.feature_code}"



# Define function to parse .txt file into Entry objects
def parse_txt_file(path):
    entries = []

    with open(path, "r", encoding="utf-8") as file:
        print(f"Reading data from {path}...")
        i = 0

        for line in file:
            data = line.split("\t")
            # PPL	populated place:	a city, town, village, or other agglomeration of buildings where people live and work
            # PPLA	first-order administrative division:	a primary administrative division of a country, such as a state in the United States
            # PPLA2	second-order administrative division:	a subdivision of a first-order administrative division
            # ...
            if data[7] == "ADM1" or data[7] == "ADM2" or data[7] == "ADM3" or data[7] == "ADM4" or data[7] == "ADM5":
                entry = Entry(data[0], data[1], data[4], data[5], data[8], data[18], data[7])
                entries.append(entry)
                i += 1


    print(f"Total entries: {i}")
    return entries
