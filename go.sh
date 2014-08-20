make -B CFLAGS='-O3 -msse3' && mvn -q install && java -Djava.library.path=. -jar target/benchmarks.jar -wi 1 -i 4 -f 1 -bm avgt -tu ns .\*ExpLoop.\*
