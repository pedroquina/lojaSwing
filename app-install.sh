#RUN echo 'nameserver 8.8.8.8' > /etc/resolv.conf
echo 'nameserver 8.8.8.8' > /etc/resolv.conf
apt update
apt install openjdk-8-jdk-headless -y
apt install maven -y
apt install git -y
apt install nano -y
