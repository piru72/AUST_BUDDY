# required gems for the operations
require "combine_pdf"
require "pdf-reader"

# Function to read every text of every routine and generate a file name for each routine in this (Year_4_Semester_2_SectionB) format
def file_name_creator(pdf, department)

  # Array to store the title of the routines
  routines = Array.new()

  i = 0

  # iterating through the pages of the pdf
  pdf.pages.each do |page|

    #extracting the texts of the pages
    writings = page.text

    # Constructing a string of a certain range from the texts of the pages
    unprocessed_title = ""
    for j in 600..705
      unprocessed_title += writings[j]
    end

    # Removing unwanted characters and adding a format to the texts
    processed_title = unprocessed_title.delete(" ").chomp.gsub("\n", "").gsub(",", "_").gsub(":", "_").gsub("(", "_").gsub(")", "")

    # Removing any unwanted character from the processed string
    final_title = processed_title.slice(0, processed_title.length - (processed_title.length - 26))
    final_title += "_#{department}"

    # adding the title to the routine
    routines << final_title
    i += 1

    # Break condition for safety as last page is usually empty
    if i + 1 == pdf.pages.size
      break
    end
  end

  # returning the list of titles
  return routines
end

# function to save the page that has the desired routine
def file_saver(pages, routines, wanted_routine)
  i = 0

  # iterating through the pages of the pdf
  pages.each do |page|
    pdf = CombinePDF.new
    pdf << page

    # If the courrent page matches with the desired routine then save the routine and break the loop
    if (routines[i] == wanted_routine)
      pdf.save("#{routines[i]}.pdf")
      break
    end

    i += 1
  end
end

# function to save all the routines

def all_routines_saver(pages, routines)
  i = 0
  # iterating through the pages of the pdf
  pages.each do |page|
    pdf = CombinePDF.new
    pdf << page

    # saving the routine
    pdf.save("#{routines[i]}.pdf")
    i += 1
  end
end

# Taking the user input to find the desired semester
def user_input()
  puts "Year"
  year = gets.chomp
  puts "Semester"
  semester = gets.chomp
  puts "Section"
  section = gets.chomp
  puts "Department"
  department = gets.chomp

  department = department.upcase
  section = section.upcase

  return "Year_#{year}_Semester_#{semester}_Section#{section}_#{department}"
end

# The main objective of this program is to find the routine of the given semester
def main()

  # Prepations to start finding the file
  intput_file_name = "Input/cse_class_routine.pdf"
  pages = CombinePDF.load(intput_file_name).pages
  pdf = PDF::Reader.new(intput_file_name)

  # Creates a list of all the routines accordingly
  routines = file_name_creator(pdf, "CSE")
  # adding an empty page
  routines << "LAST PAGE"

  # desired routine
  # wanted_routine = user_input()

  # saving the wanted routine if wanted by one only
  # file_saver(pages, routines, wanted_routine)

  all_routines_saver(pages, routines)
end

main()
