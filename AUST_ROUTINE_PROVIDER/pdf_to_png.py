# import modules
import os
from pdf2image import convert_from_path

# Define the directory that contains the PDF files
pdf_dir = 'OutputPDF'

# Get a list of all PDF files in the directory
pdf_files = [f for f in os.listdir(pdf_dir) if f.endswith('.pdf')]

# Loop through each PDF file
for pdf_file in pdf_files:
    full_path = os.path.join(pdf_dir, pdf_file)
    images = convert_from_path(full_path)
    
    # Save each page of the PDF as an image
    image_name = pdf_file[:-4]

    for i in range(len(images)):
        images[i].save('OutputImages/' + image_name + '.png', 'PNG')
        print(image_name + '.png')