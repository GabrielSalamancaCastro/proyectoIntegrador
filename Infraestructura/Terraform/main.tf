# Propósito:    crear infraestructura AWS
# Autor:        Grupo 1
# Fecha:        10.11.21
# Versión:      1.0
# ==================================================================

# ==================================================================
# Creamos nuestro VPC
resource "aws_vpc" "vpc_terraform" {                
 cidr_block       = var.terraform_vpc_cidr     
 instance_tenancy = "default"
 tags = {
 Name = "vpc_terraform"
 }
}
# ==================================================================
# Creamos un Internet Gateway "Y" lo asociamos al VPC que se acaba de crear
resource "aws_internet_gateway" "igw_terraform" {   
 vpc_id =  aws_vpc.vpc_terraform.id               
 tags = {
 Name = "igw_terraform"
 }
}
# ==================================================================
# Creamos la subnet pública
resource "aws_subnet" "redpublica_terraform" {    
 vpc_id =  aws_vpc.vpc_terraform.id
 cidr_block = var.redpublica_terraform        
 tags = {
 Name = "redpublica_terraform"
 }
}
# ==================================================================
# Tabla de ruteo para la subnet pública
resource "aws_route_table" "tabla_ruteo_terraform" {  
 vpc_id =  aws_vpc.vpc_terraform.id
 route {
   cidr_block = "0.0.0.0/0"               
   gateway_id = aws_internet_gateway.igw_terraform.id
 }
 tags = {
   Name = "tabla_ruteo_terraform"
 }
}
# ==================================================================
# Asociación de tabla de ruteo con la subnet pública
resource "aws_route_table_association" "asociacion_ruteo_redpublica_terraform" {
   subnet_id = aws_subnet.redpublica_terraform.id
   route_table_id = aws_route_table.tabla_ruteo_terraform.id
}
# ==================================================================
# EC2 

#Create security group with firewall rules
resource "aws_security_group" "grupo_seguridad_terraform" {
  name        = var.security_group
  description = "security group for jenkins"

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

 ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

 # outbound from jenkis server
  egress {
    from_port   = 0
    to_port     = 65535
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags= {
    Name = var.security_group
  }
}

resource "aws_instance" "EC2_terraform" {
  ami           = var.ami_id
  key_name = var.key_name
  instance_type = var.instance_type
  vpc_security_group_ids = ["${aws_vpc.vpc_terraform.id}"]
  security_groups= [var.security_group]
  tags= {
    Name = var.tag_name
  }
}

# Create Elastic IP address
resource "aws_eip" "EC2_terraform" {
  vpc      = true
  instance = aws_instance.EC2_terraform.id
tags= {
    Name = "jenkins_elastic_ip"
  }
}

# ==================================================================
#Bucket S3

resource "aws_s3_bucket" "S3_terraform" {
  acl = var.acl

   versioning {
    enabled = var.versioning
  }
  
}

# ==================================================================
# RDS 

resource "aws_db_instance" "RDS_terraform" {
allocated_storage = 20
identifier = "rdsprueba"
storage_type = "gp2"
engine = "mysql"
engine_version = "8.0"
instance_class = "db.t2.micro"
multi_az = false
name = "rds_terraform"
username = "admingrupo1"
password = "admingrupo1"
parameter_group_name = "default:mysql-8-0"
db_subnet_group_name = "redpublica_terraform"
vpc_security_group_ids = ["${aws_vpc.vpc_terraform.id}"]
}


resource "aws_security_group" "SG_bdd_terraform" {
  name = "SG_bdd_terraform"

  description = "RDS postgres servers (terraform-managed)"
  vpc_id = "vpc_terraform"

  ingress {
    from_port = 3306
    to_port = 3306
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Allow all outbound traffic.
  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}