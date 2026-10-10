CHRISTIAN'S RUN — ESCAPE TO CHURCH

RUNNING
Install a Java runtime (Java 8 or newer). On a desktop system with a graphical display:
    java -jar ChristianRun.jar
You can also double-click the JAR if your system associates JAR files with Java.

HOW TO PLAY
You are Christian, the blue-shirted man. The green snake with a red head pursues you.
Use arrow keys or WASD to choose a direction. Christian keeps running until you change
 direction or press Space to stop. The edges of the field stop movement; they do not wrap.
Avoid the entire snake, not just its head.

The snake grows by one segment every 1.4 seconds (up to 650 segments).
Every 0.1 seconds, your score increases by the current snake length.
Longer survival means a longer snake and faster scoring.

The church is in the top-right green area. Run into that area and press E when ready
to end the run safely and bank your score. Simply reaching the church does not end
the game: you must press E. If you are caught first, the run ends in defeat.
Press R to restart at any time.

CONTROLS
Arrows / WASD — direction
Space — stop
E — enter church (only in the top-right green area)
R — restart

SOURCE
ChristianRun.java is included alongside the JAR and inside it.
With a JDK, rebuild using:
    javac ChristianRun.java
    jar cfe ChristianRun.jar ChristianRun ChristianRun.class

BUILD NOTE
This environment has no Java compiler or Java runtime. The provided JAR contains
Java-compatible class bytecode generated directly. Its packaging and bytecode
structure were checked, but it could not be launched or play-tested here.
No external libraries, network access, or game assets are required.
