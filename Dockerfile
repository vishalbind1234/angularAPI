FROM ubuntu:22.04

RUN mkdir /root/jdkRepo && apt-get update 

RUN	apt-get -y install git && apt-get -y install git-lfs

RUN	cd /root/jdkRepo && git clone https://github.com/vishalbind1234/jdk17-2.git 

RUN	cd /root/jdkRepo/jdk17-2 && git checkout master  

RUN	cd /root/jdkRepo/jdk17-2 && git lfs pull   

RUN	cd /root/jdkRepo/jdk17-2 && mkdir /usr/lib/jvm && tar -zxvf jdk-17_bin.tar.gz -C /usr/lib/jvm   

ENV JAVA_HOME=/usr/lib/jvm/jdk-17.0.11

RUN export JAVA_HOME

ENV PATH=$PATH:/usr/lib/jvm/jdk-17.0.11/bin
RUN export PATH

WORKDIR /root/my-app
COPY . /root/my-app

RUN	cd /root/my-app && ./gradlew build

WORKDIR /root/my-app/build/libs

CMD [ "java","-jar","angularAPI-0.0.1-SNAPSHOT.jar"]

EXPOSE 8087



