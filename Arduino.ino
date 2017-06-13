char command;
String string = "";
boolean isSpeed = false;
int currentSpeed = 0;
void setup()
{
//Serial.begin(9600);
Serial3.begin(9600);
pinMode(5, OUTPUT);
pinMode(6, OUTPUT);
pinMode(7, OUTPUT);
pinMode(8, OUTPUT);
pinMode(9, OUTPUT);
pinMode(10, OUTPUT);

}
void loop()
{
if(Serial3.available() > 0){
string = "";isSpeed=false;}
while(Serial3.available() > 0)
{command = ((byte)Serial3.read());
if(command == ':')
{
break;
}
else
{
if(command == '*')
isSpeed=true;
else
string += command;
}
delay(1);
}
if(isSpeed)
{
if(string.toInt()>=0)
currentSpeed = string.toInt();
else
currentSpeed = string.toInt()+256;
}
//Backward
 if(string == "B")
  { digitalWrite(7,HIGH);
   digitalWrite(8,HIGH);
   analogWrite(9, currentSpeed);
   analogWrite(5, currentSpeed);
     } 
  


  //Forward
  if(string =="F") { 
  digitalWrite(6,HIGH);
   digitalWrite(10,HIGH); 
   analogWrite(9, currentSpeed);
   analogWrite(5, currentSpeed);
  }

//ForwardRight
if(string =="FR") { 
   digitalWrite(10,HIGH);
   analogWrite(5, currentSpeed*0.4);
    digitalWrite(6,HIGH);
     analogWrite(9, (currentSpeed));
  }

//ForwardLeft
if(string =="FL") { 
   digitalWrite(6,HIGH);
   analogWrite(9, currentSpeed*0.4);
   analogWrite(5, (currentSpeed));
   digitalWrite(10,HIGH);
  }
//BackwardLeft
if(string =="BL") { 
     digitalWrite(7,HIGH);
     analogWrite(9, currentSpeed);
     digitalWrite(8,HIGH);
     analogWrite(5, currentSpeed*0.4);
  }

//BackwardRight
if(string =="BR") { 
    digitalWrite(8,HIGH);
    analogWrite(5, currentSpeed); 
     digitalWrite(7,HIGH);
     analogWrite(9, currentSpeed*0.4);
  }


//Stop
if(string =="N") { 
   digitalWrite(6,LOW);
   digitalWrite(7,LOW);
    digitalWrite(8,LOW);
     digitalWrite(10,LOW);
    
  }

if(string =="L") { 
    digitalWrite(6,HIGH);
   analogWrite(5, currentSpeed);
   digitalWrite(8,HIGH);
    analogWrite(9, currentSpeed); 
    
  }

  if(string =="R") { 
    digitalWrite(10,HIGH);
   analogWrite(5, currentSpeed);
   digitalWrite(7,HIGH);
     analogWrite(9, currentSpeed);
    
  }





delay(3);
//Serial.println(currentSpeed);
}
