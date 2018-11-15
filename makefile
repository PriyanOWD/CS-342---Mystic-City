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
	DangerZone.java \
	SafeZone.java \
	Direction.java \
	Artifact.java \
	Armor.java \
	Food.java \
	Weapon.java \
	Bruiser.java \
	Healer.java \
	Character.java \
	Player.java \
	Attacker.java \
	Tank.java \
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
	Equip.java \
	Consume.java \
	Inventory.java \
	Inspect.java \
	Go.java \
	GoPlayer.java \
	GoNPC.java \
	Market.java \
	Buy.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
