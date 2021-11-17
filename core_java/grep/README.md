# Introduction
This Java application mimics the usage of the `grep` utility commonly found in Unix operating systems. Given a regex pattern, a root directory containing files to search through and another directory to store the output, the application returns every line from every file recursively searched that matches the passed pattern.

# Quick Start
The application takes 3 arguments: a regex pattern, a path to a root directory containing the files to be searched and a path to a directory in which the results will be written to a text file.

# Implementation

## Pseudocode
The process() method implements the following pseudocode.
```
matchedLines = []
fileList = listFiles(rootDir)

for file in fileList
    lines = readLines(file)
    for line in lines
        if containsPattern(line)
            matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issue
The application is limited by the size of the files being read, we can fix this by updating the methods that return `Lists` to `Buffer` or `Stream`.

# Test
The application was tested with a 5.21 MB text file stored in nested directories in the data/ folder. The application finds this file using a recursive search method and writes matched lines to a text file that gets stored in the out/ folder.

# Deployment
The application uses Docker to allow for easier distribution on various operating systems and configurations. The application is packaged with Maven and then deployed in a Docker container.

# Improvement
This application could be improved by updating the recursive search method to filter out non-text files, or even allow for specifying which file extensions to find/filter. The writeToFile() method could be updated to include a timestamp in the filename rather than writing to the same file every time. Finally, the memory issue can be solved so the application can handle much larger files.