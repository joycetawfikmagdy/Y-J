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
analogWrite(9, currentSpeed);
digitalWrite(8,HIGH);
digitalWrite(10,LOW);
analogWrite(5, currentSpeed);
digitalWrite(6,HIGH);
digitalWrite(7,LOW);
delay(3);
//Serial.println(currentSpeed);
}
