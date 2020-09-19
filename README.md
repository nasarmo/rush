## Welcome to...The rush!

### Description:

The application will automatically load the provided rushing.json file (can be found in the resource folder.
It will provide 10 players per page (passed from the UI).
If you select a filter, it will sort the entire data set based on the provided filter (by longest yard, touchdowns etc).
You are also able to search for players in the text field and by clicking on the magnifying glass. If no name is provided, it will return all players.
If you click on the top right of the page, you are able to export the current data set to a csv file.

## Local Setup Docker Instructions:

1)`mvn clean install`

2)`cd ui && npm install && npm run build`

3)make sure you are in root directory `cd  ..`

4)`docker build -t rush .`

5)`docker run -p8080:8089 rush`

6)hit `localhost:8089/`

## Local Setup

1. `npm install -g @vue/cli` used to set up vue cli
2. `cd ui` moves you to ui folder
3. `vue add vuetify` adds veutify to ui
4. `npm run serve` runs the UI  
5. run springboot application 'RushApplication'


