mvn -DskipTests -Dmaven.javadoc.skip=true clean package
docker-compose -f docker-compose.yml down
docker-compose -f docker-compose.yml up --build