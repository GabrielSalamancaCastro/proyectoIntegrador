# ==================================================================
# Propósito:    declaramos que proveedor cloud queremos usar
# Autor:        Grupo 1
# Fecha:        10.11.21
# Versión:      1.0
# ==================================================================

# ==================================================================
terraform {
 required_version = ">=0.12"
 required_providers {
   aws = {
     source = "hashicorp/aws"
     version = "~> 3.20.0"
   }
 }
}
# ==================================================================
provider "aws" {
 shared_credentials_file = "~/.aws/credentials"
 region = "sa-east-1"
}
# ==================================================================