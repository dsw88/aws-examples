# -*- mode: ruby -*-
# vi: set ft=ruby :
# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

# Install Maven and Java 7
$script = <<PROVISION
sudo apt-get update
sudo apt-get install --assume-yes --force-yes git vim openjdk-7-jdk maven
echo "export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64" >> /home/vagrant/.bashrc
PROVISION

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "ubuntu/trusty64"

  config.vm.provision "shell", inline: $script
  config.vm.network :private_network, ip: "192.168.53.100"
  config.vm.network :forwarded_port, guest: 3000, host: 3000
  config.vm.provider :virtualbox do |v|
    v.memory = 2048
    v.cpus = 4
  end
end
