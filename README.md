# Romain Coins and Tensorflow

## Overview

An experiment with using downloaded coins with the faces of emperors and sending that through Tensorflow, to see if 
Image Learning will recognize the images with some accuracy. It uses some basic Java scraping and Tensorflow.

See the article I wrote on this use case for more details.

## Setup

### Java

The project has a Java part, which uses JSoup to get the image links and then downloads them to an images directory.

To scrape the image urls, you can run `Main` class. Alternatively, use the jar file I included and run:

`java -jar jsoup-image-scraping-1.0.jar`,

or generate a jar yourself by running:

`mvn clean install`  

You will, of course, need a Java Runtime (JRE) or JDK to run this Java program.

### Tensorflow

1° Install Tensorflow using pip (`pip install tensorflow`), *preferably in a virtual env*. If you run into an issue with 
tensorflow not working with 3.6, you can do install a more recent version via `pip install tf-nightly`.  
2° Download/clone the Tensorflow project from [GitHub][1].  
3° Now you can follow the instructions given in this [article][2].

[1]:https://github.com/tensorflow/tensorflow
[2]:https://medium.com/@linjunghsuan/create-a-simple-image-classifier-using-tensorflow-a7061635984a
