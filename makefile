JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	GameTester.java \
	Game.java \
	CleanLineScanner.java \
	KeyboardScanner.java \
	Place.java \
	Direction.java \
	Artifact.java \
	Character.java \
	Player.java \
	NPC.java \
	DecisionMaker.java \
	UI.java \
	AI.java \
	Move.java \
	Quit.java \
	Commands.java \
	Look.java \
	Get.java \
	GetPlayer.java \
	GetNPC.java \
	Drop.java \
	DropNPC.java \
	DropPlayer.java \
	Use.java \
	Inventory.java \
	Go.java \
	GoPlayer.java \
	GoNPC.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
