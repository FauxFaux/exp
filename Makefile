MAINFLAGS=-I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -fPIC

libexp.so: lib.cpp exp.c src/main/java/com_goeswhere_jmh_Exp.h
	$(LINK.cc) $(MAINFLAGS) lib.cpp -o libexp.so


