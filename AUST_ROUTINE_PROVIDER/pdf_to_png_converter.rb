require "mini_magick"

# Open the PDF
pdf = MiniMagick::Image.open("Year_1_Semester_1_SectionA_CSE.pdf")

# Convert the first page of the PDF to an image
image = pdf.clone
image.format "png"
image.write "page1.png"