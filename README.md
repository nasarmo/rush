###  UI

1. `npm run serve` runs the UI
2. run the springboot application


### db schema changes
1. update the ```./schema/create_db_postgres.sql``` file with the updated schema
2. recreate the db in docker-compose ```docker-compose rm -fs``` ```docker-compose up -d```