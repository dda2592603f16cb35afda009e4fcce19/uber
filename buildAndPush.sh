/home/nick/Downloads/apache-maven-3.6.1/bin/mvn package -DskipTests &&
docker build -t foodtrucks . &&
docker login &&
docker tag foodtrucks bakkegaard/foodtrucks &&
docker push bakkegaard/foodtrucks
