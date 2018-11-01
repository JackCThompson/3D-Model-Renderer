These are the text files that the program uses to load in models.

The format for these files is as follows:

<x y z>, <x y z>, <x y z>, <x y z>;
<r g b i j k>, <r g b i j k l>;

//points are formed by cartesian values seperated by spaces
//list all points seperated by commas
//section ends with a semi-colon

//polygons are formed by rgb values for the polygon color 
//    followed by a series of index values corresponding to the points in the order they were listed (starting at 0)
//all values seperated by spaces
//points should be listed such that they appear in clockwise order when viewed from the front 
//    (polygons do not render when viewed from the back)
//polygons separated by commas
//esction ends with semi colon
