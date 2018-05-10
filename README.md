# Regexp : création d'automate

Projet sur les automates

#### Entrée du programme
Ce programme s'execute en ligne de commande sur un terminal Linux
 
On entre une regexp en Java dont les règles sont citées ci-dessous.
 
#### Execution
<code> $javac RegexpParser.java </code>
<code> $java RegexpParser.java </code>

#### Définition d'une lettre d'un mot :
	[A] = une lettre
	[ABC] = les lettres présentes entre les accolades (Cela est considéré comme a ou b ou c)
 
#### Définition des répetitions :
	[X]* = Caractère X 1 ou plusieurs fois
	[X]? = Caractère X une unique fois
	
#### Règles :
	Les "[]" pour encadrer les lettres sont obligatoires
	Les "[]" sont tonjours suivis par * ou ?
	
#### Exemple de regex à entrer
	"^[ab]*[c]?[z]*[k]?" : 
	- Les mots commençant par a ou b (1 ou plusieurs fois) contenant c (une fois), z (1 ou plusieurs fois) et k (une fois)

 
 
 
 
