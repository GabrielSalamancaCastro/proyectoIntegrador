# ==================================================================
# EC2
#outputs.tf
output "public_ip" {
value = aws_instance.EC2_terraform.public_ip
}
output "public_dns" {
value = aws_instance.EC2_terraform.public_dns
}

# ==================================================================
#Bucket S3

output "s3_bucket_name" {
  value = "S3_terraform"
}
output "s3_bucket_region" {
    value = "sa-east-1"
}

