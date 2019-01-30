/*
 ParseEq is a an importable Java-based parser that takes an equation (e.g. (1+(5*(8/10))) ) as a string and calculate the sum. The returned value is also a string thus the user can convert it into a preferable type (integer, float or string). ParseEq can efficiently handle numbers and operations, including negative numbers, parentheses, and Exponentiations. ParseEq can be used as part of Java library and it is mostly useful when building compilers and interpreters. The file `ParseEq.java` is the the file that need to be imported.
 
 Assuming `ParseEq.java` is located in the same the directory as the importing program and has been successfully compiled, to import ParseEq, first define a new ParseEq class and pass the equation to be evaluated as a string to this class. The returned value is the sum of the equation which is also of type string.
 */

import java.io.*;
import java.util.*;

class  ParseEq
{
	public static String ParseEq(String sequeuce)
		throws FileNotFoundException {
            String testNum = sequeuce;
            Scanner contents = new Scanner(testNum);
            String equation = contents.nextLine();
            String newEquation="";
            String solution = "";
            
            //Tokenize equation for evaluation
            for(int i=0;i<equation.length();i++) {
                if((i==0)&&(equation.charAt(i)=='-')) {
                    newEquation="! "+equation.charAt(i);
                }
                else if(equation.charAt(i)=='+'||equation.charAt(i)=='%'||equation.charAt(i)=='/'||equation.charAt(i)==')'||equation.charAt(i)=='-') {
                    newEquation=newEquation+" "+equation.charAt(i)+" ";
                }
                else if((equation.charAt(i)=='(')&&(equation.charAt(i+1)=='-')) {
                    newEquation=newEquation+equation.charAt(i)+" ~ "+equation.charAt(i+1);
                    i++;
                }
                else if((equation.charAt(i)=='(')&&(equation.charAt(i+1)!='-')) {
                    newEquation=newEquation+" "+equation.charAt(i)+" ";
                }
                else if((equation.charAt(i)=='*')&&(equation.charAt(i+1)=='*')) {
                    newEquation=newEquation+" "+equation.charAt(i)+equation.charAt(i+1)+" ";
                    i++;
                }
                else if((equation.charAt(i)=='*')&&(equation.charAt(i+1)!='*')) {
                    newEquation=newEquation+" "+equation.charAt(i)+" ";
                }
                else {
                    newEquation=newEquation+equation.charAt(i);
                }
            }
            newEquation=newEquation+" "+";";
            solution = solve(newEquation);
            return solution;
        }

		//Solve equation for value enquiry 
		public static String solve(String console)
		throws FileNotFoundException {
				Scanner sentence = new Scanner(console);
				ArrayList<String> equation= new ArrayList<String>();
                String token=" ";
				float sum=0;
				int tokenCount=0;
				int startParenth=0;
				int endParenth=0;
				int numEliminated=0;
				int differ=0;
				int numRemain=0;
				
				while(!token.equals(";")) {
					token=sentence.next();
					equation.add(token);
					tokenCount++;
				}
            
                //Start calculating figures between parentheses
				while(equation.contains("(")) {
					//Find the last opening parentheses
					int q=-1;			
					for(String u:equation) {
						q++;
						if(u.equals("(")) {
							startParenth=q;	
						}
					}
					
					//Find the first closing parentheses
					for(int i=startParenth;i<tokenCount;i++) {
						if(equation.get(i).equals(")")) {
							endParenth=i;
							break;	
						}
					}
                    
					numEliminated=0;
                    
                    //Calculate according to the correct order of arithmetic operatons
                    for(int i=startParenth;i<=endParenth;i++) {
                        if(equation.get(i).equals("!")||equation.get(i).equals("~")) {
                            sum=Float.parseFloat(equation.get(i+1));
                            equation.set(i,Float.toString(sum));
                            equation.remove(i+1);
                            i--;
                            numEliminated++;
                            sum=0;
                            endParenth--;
                        }
                    }
                    for(int i=startParenth;i<=endParenth;i++) {
                        if(equation.get(i).equals("**")) {
                            sum=(float)(Math.pow(Integer.parseInt(equation.get(i-1)),Integer.parseInt(equation.get(i+1))));
                            equation.set(i-1,Float.toString(sum));
                            equation.remove(i);
                            equation.remove(i);
                            i--;
                            i--;
                            numEliminated++;
                            numEliminated++;
                            sum=0;
                            endParenth--;
                            endParenth--;
                        }
                    }
					//Solve for "*" & "/"
					for(int i=startParenth;i<=endParenth;i++) {
						
							if(equation.get(i).equals("*")) {
								sum=Float.parseFloat(equation.get(i-1))*Float.parseFloat(equation.get(i+1));
								equation.set(i-1,Float.toString(sum));
								equation.remove(i);
								equation.remove(i);
								i--;
								i--;
								numEliminated++;
								numEliminated++;
								sum=0;
								endParenth--;
								endParenth--;
							}
							else if(equation.get(i).equals("/")) {
								sum=Float.parseFloat(equation.get(i-1))/Float.parseFloat(equation.get(i+1));
								equation.set(i-1,Float.toString(sum));
								equation.remove(i);
								equation.remove(i);
								i--;
								i--;
								numEliminated++;
								numEliminated++;
								sum=0;
								endParenth--;
								endParenth--;
							}
					}
					//Solve for "+" & "-"
					for(int i=startParenth;i<=endParenth-numEliminated;i++) {
						if(equation.get(i).equals("+")) {
							sum=Float.parseFloat(equation.get(i-1))+Float.parseFloat(equation.get(i+1));
							equation.set(i-1,Float.toString(sum));
							equation.remove(i);
							equation.remove(i);
							i--;
							i--;
							numEliminated++;
							numEliminated++;
							sum=0;
							endParenth--;
							endParenth--;
						}
						else if(equation.get(i).equals("-")) {
							sum=Float.parseFloat(equation.get(i-1))-Float.parseFloat(equation.get(i+1));
							equation.set(i-1,Float.toString(sum));
							equation.remove(i);
							equation.remove(i);
							i--;
							i--;
							numEliminated++;
							numEliminated++;
							sum=0;
							endParenth--;
							endParenth--;
						}
					}
					
					//Eliminate parentheses
					differ=endParenth-startParenth;
					
					if((differ-numEliminated)==2) {
						equation.remove(startParenth);
						equation.remove(startParenth+1);
					}
								
				}
			
			for(String e:equation) {
					numRemain++;
				}
			
			numEliminated=0;
			
			//Calculating figures without parentheses
			while(!equation.contains("(")) {
                    //Calculate according to the correct order of arithmetic operatons
                    for(int i=0;i<numRemain;i++) {
                        if(equation.get(i).equals("!")||equation.get(i).equals("~")) {
                            sum=Float.parseFloat(equation.get(i+1));
                            equation.set(i,Float.toString(sum));
                            equation.remove(i+1);
                            i--;
                            numEliminated++;
                            sum=0;
                            numRemain--;
                        }
                    }
                    for(int i=0;i<numRemain;i++) {
                        if(equation.get(i).equals("**")) {
                            sum=(float)(Math.pow(Integer.parseInt(equation.get(i-1)),Integer.parseInt(equation.get(i+1))));
                            equation.set(i-1,Float.toString(sum));
                            equation.remove(i);
                            equation.remove(i);
                            i--;
                            i--;
                            numEliminated++;
                            numEliminated++;
                            sum=0;
                            numRemain--;
                            numRemain--;
                        }
                    }
					//Solve for "*" & "/"
					for(int i=0;i<numRemain;i++) {
						if(equation.get(i).equals("*")) {
							sum=Float.parseFloat(equation.get(i-1))*Float.parseFloat(equation.get(i+1));
							equation.set(i-1,Float.toString(sum));
							equation.remove(i);
							equation.remove(i);
							i--;
							i--;
							numEliminated++;
							numEliminated++;
							sum=0;
							numRemain--;
							numRemain--;
						}
						else if(equation.get(i).equals("/")) {
							sum=Float.parseFloat(equation.get(i-1))/Float.parseFloat(equation.get(i+1));
							equation.set(i-1,Float.toString(sum));
							equation.remove(i);
							equation.remove(i);
							i--;
							i--;
							numEliminated++;
							numEliminated++;
							sum=0;
							numRemain--;
							numRemain--;
						}
					}
					//Solve for "+" & "-"
					for(int i=1;i<numRemain;i++) {
						
						if(equation.get(i).equals("+")) {
							sum=Float.parseFloat(equation.get(i-1))+Float.parseFloat(equation.get(i+1));
							equation.set(i-1,Float.toString(sum));
							equation.remove(i);
							equation.remove(i);
							i--;
							i--;
							numEliminated++;
							numEliminated++;
							sum=0;
							numRemain--;
							numRemain--;
						}
						else if(equation.get(i).equals("-")) {
							sum=Float.parseFloat(equation.get(i-1))-Float.parseFloat(equation.get(i+1));
							equation.set(i-1,Float.toString(sum));
							equation.remove(i);
							equation.remove(i);
							i--;
							i--;
							numEliminated++;
							numEliminated++;
							sum=0;
							numRemain--;
							numRemain--;
						}
					}
					if(numRemain==2) {
						break;
					}
				}
				return equation.get(0);
			}
}
