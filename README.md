# ParseEq

**ParseEq** is a an importable Java-based parser that takes an equation (e.g. (1+(5*(8/10))) ) as a *string* and calculate the sum. The returned value is also a *string* thus the user can convert it into a preferable type (integer, float or string). ParseEq can efficiently handle numbers and operations, including negative numbers, parentheses, and Exponentiations. ParseEq can be used as part of Java library and it is mostly useful when building compilers and interpreters. The file `ParseEq.java` is the the file that need to be imported. 
Note: The `Demo.java` is only for demonstrating how `ParseEq.java` will work in reality. 

## Importing and Running 

Assuming `ParseEq.java` is located in the same the directory as the importing program and has been successfully compiled, to import ParseEq, first define a new ParseEq class and pass the equation to be evaluated as a string to this class:
```
ParseEq solveFor = new ParseEq();
solution = solveFor.ParseEq(equation);
```
The returned value is the sum of the equation which is also of type string. 

### Example 1:
x = 1+3*5-10/2
```
String x = "1+3*5-10/2";
ParseEq solveFor = new ParseEq();
solution = solveFor.ParseEq(x);
```

The returned value is the string `11.0`.

### Example 2:
y = (36+(5*10)-((104-9)/20))
```
String y = "(36+(5*10)-((104-9)/20))";
ParseEq solveFor = new ParseEq();
solution = solveFor.ParseEq(y);
```

The returned value is the string `81.25`.

### Example 3:
z = 
