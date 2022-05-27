# ==================================================================
# Proposito:    declaramos todas las variables que vamos a usar
# Autor:        Grupo 1
# Fecha:        10.11.21
# Version:      1.0
# ==================================================================

variable "aws_region_id" {
   description = "la region"
   type        = string
   default = "sa-east-1a"
}
variable "terraform_vpc_cidr" {
   description = "Security Group Terraform"
   type        = string
   default = "10.0.0.0/16"
}

variable "redpublica_terraform" {
   description = "subnet con acceso a internet"
   type        = string
   default = "10.0.0.128/26"
}
# ==================================================================
#EC2 


variable "key_name" { 
    description = " SSH keys to connect to ec2 instance" 
    default     =  "claves_terraform" 
}

variable "instance_type" { 
    description = "instance type for ec2" 
    default     =  "t2.micro" 
}

variable "security_group" { 
    description = "Name of security group" 
    default     = "grupo_seguridad_terraform" 
}

variable "tag_name" { 
    description = "Tag Name of for Ec2 instance" 
    default     = "EC2_terraform" 
} 
variable "ami_id" { 
    description = "AMI for Ubuntu Ec2 instance" 
    default     = "ami-0e66f5495b4efdd0f" 
}

# ==================================================================
#Bucket S3

variable "versioning" {
    type        = bool
    description = "(Optional) A state of versioning."
    default     = true
}
variable "acl" {
    type        = string
    description = " Defaults to public-read-write "
    default     = "public-read-write"
}


