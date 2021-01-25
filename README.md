# Run the application
```shell
./gradlew run
```

# Arduino example code
```
String readString;

void setup() {
    Serial.begin(9600);
    delay(500);
}

void loop() {
    while (Serial.available()) {
        delay(2);  //delay to allow byte to arrive in input buffer
        char c = Serial.read();
        readString += c;
    }

    if (readString.length() >0) {
        Serial.print("Response from Arduino: " + readString);
        readString="";
    }
}
```