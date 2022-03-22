docker network create jpj_training_network

docker rm jpj_training_db
docker run --name jpj_training_db --network jpj_training_network --network-alias jpj_db -v D:\Workspace\data_file\tcsens_training:/var/lib/mysql -w /app -e MYSQL_USER=app -e MYSQL_DATABASE=jpj-training -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 mariadb:10.6.5