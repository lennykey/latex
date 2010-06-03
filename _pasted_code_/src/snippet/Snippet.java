package snippet;

public class Snippet {
	%Praxisbericht 1
	% Firma: Explido
	%
	%
	%
	%
	
	\documentclass[titlepage, 12pt,a4paper]{scrartcl}
	%scrartcl
	\usepackage[ngerman]{babel}
	%\usepackage[latin1]{inputenc}
	\usepackage[T1]{fontenc}
	\usepackage{ucs}            % Eventuell benötigt
	\usepackage[utf8x]{inputenc}
	\usepackage{graphicx}
	\usepackage{listings}
	\usepackage[hyphens]{url}
	%\usepackage{breakurl}
	\usepackage{hyperref}
	\usepackage[usenames]{color}
	\definecolor{light-gray}{gray}{0.90}
	\usepackage[fixlanguage]{babelbib}
	\usepackage{listings}
	%\lstset{numbers=left, numberstyle=\tiny, numbersep=5pt}
	%\lstset{language=Perl}
	\lstloadlanguages{bash,XML,HTML, PHP}
	\selectbiblanguage{german}
	\usepackage{makeidx}
	%\usepackage{pifont}
	\makeindex
	%\usepackage{fancyhdr}
	%\setlength{\headheight}{15.2pt}
	%\pagestyle{fancy}
	
	
	\author{Andres Cuartas}
	\title{- Explido Webmarketing - \\ Praxisbericht 2\\ }
	%\date{11-Dez-2007}
	\pagestyle{myheadings}
	 \markright{Andres Cuartas}
	
	%\lstset{language=XML, stringstyle=\ttfamily, tabsize=2, basicstyle=\small, breaklines=true, backgroundcolor=\color{light-gray}, frameround=fttt}
	\lstset{
		inputencoding=utf8x,
		extendedchars=\true,
		language=XML,
		basicstyle=\tiny,
		keywordstyle=\bfseries\color{green},
		identifierstyle=,
		%commentstyle=\color{gray},	
		%stringstyle=\itshape\color{darkred},
		numbers=left,
		numberstyle=\tiny,
		stepnumber=1,
		breaklines=true,
		frame=none,
		showstringspaces=false,
		tabsize=4,
		backgroundcolor=\color{light-gray},
		captionpos=b,
		float=htbp,
		frameround=fttt
	}
	%              
	% WORKAROUND, damit lstlistoflistings funktioniert: 
	% Quelle: http://www.komascript.de/node/477
	%
	\makeatletter% --> De-TeX-FAQ
	\renewcommand*{\lstlistoflistings}{%
	  \begingroup
	    \if@twocolumn
	      \@restonecoltrue\onecolumn
	    \else
	      \@restonecolfalse
	    \fi
	    \lol@heading
	    \setlength{\parskip}{\z@}%
	    \setlength{\parindent}{\z@}%
	    \setlength{\parfillskip}{\z@ \@plus 1fil}%
	    \@starttoc{lol}%
	    \if@restonecol\twocolumn\fi
	  \endgroup
	}
	\makeatother% --> \makeatletter 
	
	\begin{document} 
	
	\maketitle
	
	\newpage
	
	\tableofcontents
	
	\newpage
	
	\section{Durchgeführte Arbeiten}
	
	\subsection{Wie Ist die IP}
	
	\subsubsection{Beschreibung}
	Die Seite \emph{www.wieistdieip.de} gibt es seit einiger Zeit im Netz und basiert auf PHP. Die Seite bietet die Möglichkeit, die eigene IP Adresse auszulesen und anzuzeigen. Darüber hinaus ist es möglich in ein Formularfeld, eine oder mehrere URLs einzugeben und sich weitere Informationen über die gewünschte Seite als Tabelle anzeigen zu lassen. Die folgende Informationen wurden bisher ermittelt und ausgegeben:
	
	\begin{figure}[h]
	\begin{center}
	\includegraphics[width=10cm]{wieistdieipAusgabeInformationen.jpg}
	\caption{Ist Zustand IP Informationen} \label{ist} 
	\end{center}
	\end{figure}
	
	% Hier der Versuch die Informationen als Tabelle aufzubauen
	%\begin{center}
	%\begin{tabular}{| c | c | c | c | c | c |}
	%\hline
	%\textbf{URL} & \textbf{IP} & \textbf{Type} & \textbf{PRIO} & \textbf{TTL} & \textbf{NS/MX-Server} \\ \hline
	%www.explido.de &  87.106.189.188 & A &  & 86400 &  \\  \hline
	%explido.de &  & SOA & &  81738 &  \\ \hline 
	%mx00.kundenserver.de & & 212.227.15.134 & A & &	997 \\ \hline
	%\end{tabular}
	%\end{center}
	
	Zuerst wird überprüft, ob die übergebenen URLs korrekt eingegeben worden sind. Ist die Überprüfung positiv ausgefallen, werden die URLs an die PHP Funktion    
	
	\begin{verbatim}
	dns_get_record($domain);
	\end{verbatim}
	
	übergeben, die in einer Subroutine nach DNS\index{DNS} Einträgen sucht. Ist einer dieser Einträge ein \emph{CNAME}\index{CNAME}\footnote{http://de.wikipedia.org/wiki/CNAME\_Resource\_Record} Eintrag, wird die Subroutine so lange rekursiv aufgerufen, bis die Zieladresse (target) und die übergebene Adresse (host) identisch sind. Ist das der Fall, wird die Routine beendet und die DNS Informationen der übergebenen Hosts formatiert als Tabelle ausgebeben. Ein CNAME ist im Grunde ein Alias auf einen bestehenden DNS-Namen bzw. auf eine Domain. Diese kann man z.B. benutzen, um auf andere Domains  oder auf bestehende Unterverzeichnisse weiterzuleiten. CNAMES\footnote{http://support.hostloco.com/de/CName} werden auch oft benutzt, um Domains\index{Domain} für einen späteren Zeitpunkt bereits frühzeitig zu registrieren.
	\subsubsection{Überblick über neue Funktionalität}
	Um die Seite noch attraktiver zu machen und mehr User anzulocken, sollte die Funktionalität der Seite, um folgende Punkte erweitert werden.   
	
	\begin{itemize}
	\item{Ausgabe der Information über den Status und die Antwortzeit der übergebenen Seite}
	\item{Whois Anfrage an dazu zuständige Registrierungsstellen}
	\item{Zusätzliche Anfrage und Ausgabe von SOA Informationen}
	\item{Zurückgelieferte IP Adressen sollten mit einem Klick markiert werden}
	\item{Anzeigen des Datums und der aktuellen Uhrzeit unterhalb der momentanen IP-Adresse}
	\item{Alternatives Layout für die Printfunktion}
	\end{itemize}
	
	\subsubsection{Analyse}
	Als erstes stand die Analyse des Codes und vor allem, die des rekursiven Aufrufes der Funktion \emph{ueberpruefung()}, die zusätzliche Ziele (Targets) zur Überprüfung und Ausgabe von Informationen ermittelt. Nach der Analyse der Grundfunktionalität des schon bestehenden Systems, erfolgte die Einbindung weiterer Funktionen zur Ermittlung des Status und der Antwortzeit von Domains.
	
	\subsubsection{Status und Antwortzeit}
	Die erstellte Status-Funktion \emph{statusReturn()} basiert auf die PHP interne \emph{get\_headers()} Funktion, die eine URL\index{URL} als Parameter erwartet. Diese sammelt Informationen über die übergebene Seite und gibt ein Array zurück, in dem die gesammelten Informationen stehen. Im ersten Arrayfeld \emph{[0]} steht wiederum ein String mit Headerinformationen, dieser String wurde dann mit Hilfe von \emph{explode()} so vorbereitet, um den gewünschten Status zurückgeben zu können. 
	
	Um die Antwortzeit der Seite zu ermitteln, wurde die Funktion \emph{antwortZeit()} erstellt, die bei Ausführung die momentane Systemzeit ermittelt. Die Funktion erwartet wiederum eine URL, die als Ziel für die Anfrage dient. Dabei wird ein Socket\index{Socket} mittels \emph{fsocketopen()} geöffnet und wieder geschlossen und danach nochmals gemessen, wie lange diese Aktion gedauert hat. Die Ausgabe wird dann vorformatiert und zurückgegeben. 
	
	Beide Funktionen wurden in die entsprechenden Funktionen zur Ausgabe der Tabelle eingebaut und erfolgreich ins System übernommen.
	
	\subsubsection{Whois?}
	Mit \emph{whois}-Anfragen\index{WHOIS}\footnote{http://www.denic.de/de/whois/index.jsp} ist es möglich Informationen über z.B. Domaininhaber zu ermitteln. Diese Informationen sind öffentlich zugänglich und können bei den dafür zuständigen Registrierungsstellen von jedem abgefragt werden. Dabei basiert die Whois-Anfrage auf dem RFC 3912\footnote{http://tools.ietf.org/html/rfc3912} Protokoll, das Klartextkommunikation auf dem Port 43/TCP definiert.
	
	Um whois Informationen von den entsprechenden Registrierungsstellen zu erhalten, wird auf der Startseite per Klick eine Anfrage auf die Seite \emph{whois.php} generiert. Diese nimmt die übergebene URL entgegen und übergibt die Anfrage an das Linux Konsolenprogramm \index{Linux} \emph{whois}. Das Programm wird durch die PHP-Funktion \emph{shell\_exec()} angestoßen und \emph{whois} sendet die Anfrage an den entsprechenden Server. Der Vorteil dieser Variante ist, dass die Anfrage automatisch an die richtige Stelle gesendet wird. Es wäre auch möglich eine PHP-Funktion dafür zu nutzen, die aber nicht so viel Feedback bietet, wie es mit dem Linux Programm der Fall ist. Darüber hinaus, müssten für alle möglichen Toplevel-Domains im Vorhinein die entsprechenden Registrierungstellen ermittelt werden. Dies wird vom Terminalbefehl automatisch ermittelt. Das Terminal leitet die empfangenen Informationen als String wiederum an die aufrufende PHP-Funktion. Dieser String braucht nur noch mit Hilfe von \emph{nl2br()} für die HTML Ausgabe vorbereitet und per \emph{echo()} ausgegeben zu werden.  
	
	\subsubsection{SOA}
	Als weiterer wichtiger Punkt wurde eine neue Seite erstellt, die SOA\footnote{http://encyclopedia2.thefreedictionary.com/start+of+authority} (Start of authority) Einträge zurück liefert. Ein SOA-Record enthält wichtige Angaben zur Verwaltung der Zone\index{Zone}, insbesondere zum Zonentransfer\footnote{http://de.wikipedia.org/wiki/Start\_of\_Authority}. Diese Funktionalität wurde mit der bereits erwähnten PHP\index{PHP} Funktion \emph{dns\_get\_record()}\footnote{http://us3.php.net/manual/en/function.dns-get-record.php} ermöglicht. Dieser werden aber bestimmte Parameter übergeben, um speziell SOA Informationen abzurufen. Diese Informationen werden wiederum in einem Array gespeichert und als Tabelle formatiert ausgegeben.
	
	Der Aufruf der SOA-Records\index{SOA} geschieht einfach per Klick auf den Link \emph{[SOA]}, der sich auf der Startseite befindet. Dabei wird per \emph{GET}-Aufruf\index{GET}\footnote{http://www.selfphp.de/praxisbuch/praxisbuchseite.php?site=183\&group=32}, die URL an die \emph{soa.php} Seite geschickt, die wiederum für die Anfrage und Ausgabe der SOA-Daten zuständig ist. 
	\subsubsection{Markierung der IPs}
	Um die Bedienbarkeit der Seite zu verbessern wurden die angezeigten IP-Adressen mit Hilfe von JavaScript\index{JavaScript} so vorbereitet, dass mit nur einem Klick die gesamte Adresse markiert wird. Die Idee dahinter war, dass der User so mit wenig Aufwand, die Adresse zwischenspeichern kann. 
	
	\lstset{
		language=PHP
	}
	\begin{lstlisting}
	function ipReturn($ip){
	return "<input id=\"ip\"  type=\"text\" value=\"" . $ip. "\" onClick=\"this.select()\" onMouseOver=\"this.style.backgroundColor = '#E8E8E8'\" onMouseOut=\"this.style.backgroundColor = 'white'\" readonly  />";
	}
	\end{lstlisting}
	
	Um ein Text zu markieren muss man normalerweise im Browser mit der Maus über den Text fahren und ein Doppelklick ausführen. Probleme gibt es wenn eine Textpassage Sonderzeichen enthält. Bei einer IP machen die Punkte (127.0.0.1) beim Markieren der IP Adresse Probleme, da bei einem Doppelklick nur der Teil bis zum Punkt markiert wird und der Rest nicht. Die Funktion \emph{ipReturn(\$ip)} bewirkt, falls eine IP-Adresse übergeben wird, dass der übergebene Text mit HTML und JavaScript gewrapped wird und das Auswählen des gesamten Textfeldes durch einfaches Klicken ermöglicht wird. Das Zwischenspeichern ist dann mittels \emph{STRG-C} dann leicht möglich. 
	
	\subsubsection{Datum und Uhrzeit}
	Damit der User genau weiß, wann er die Seite betreten hat oder, wenn er einen Ausdruck der Ergebnisse erstellt, auch die Uhrzeit im Ausdruck hat, wurden unterhalb der IP-Ausgabe zusätzliche Informationen eingebettet. Beide Ausgaben wurden mit Hilfe der Funktion \emph{date()} realisiert, wobei die Zeitzone mittels \emph{date\_default\_timezone\_set()} auf UTC+1\index{UTC} gestellt werden sollte, um die richtige Zeit ausgeben zu können. Unter \url{http://us2.php.net/manual/en/timezones.php} werden die möglichen Parameter aufgelistet. Als Parameter wurde \emph{Europe/Berlin} ausgewählt.
	
	\subsubsection{Alternative Printausgabe der Seite}
	%\paragraph
	Um weiteren Komfort für den User der Seite zu bieten, wurde ein alternatives Druck-Stylesheet für die Seite erstellt. Hierbei werden die Uhrzeit und das Datum anders positioniert und auch die Überschrift der Tabelle und deren Breite gesetzt, damit die Informationen auch auf die Breite der Seite richtig dargestellt werden. Auch werden Informationen, wie die Formularfelder ausgeblendet, die für den Ausdruck keine Relevanz haben. Diese Ausblendung von Information geschieht am alternativen Stylesheet mit Hilfe der CSS-Angabe\index{CSS} \emph{display: none}, dabei werden DIV-Container als nicht sichtbar definiert und somit auch nicht mehr gerendert.
	
	%\paragraph
	Damit das Print-Stylesheet auch angesprochen wird, falls der User die Druckfunktion des Browsers benutzt, muss folgende Angabe im Head Bereich des HTML-Codes eingebettet werden.
	
	\lstset{
		language=HTML	
	} 
	
	\begin{lstlisting}
	<head>
	<link rel="stylesheet" type="text/css" media="print" href="print.css" />
	</head>
	\end{lstlisting}
	
	Dabei ist die Anweisung \emph{media="print"} die entscheidende, damit die Druckversion sichtbar wird. Auch könnte man für verschiedene Ausgabegeräte ein eigenes Stylesheet definieren. Folgende Angaben\footnote{http://de.selfhtml.org/css/formate/einbinden.htm} statt \emph{print} sind unter anderen für verschiedene Ausgabegeräte möglich:
	
	\begin{itemize} 
	\item{„braille“ für Ausgabegeräte mit sog. \emph{Braille-Zeile}}
	\item{„handheld“ für Web fähige elektronische Organizer}
	\item{„projection“ für Beamer}
	\item{„tv“ für TV-Ähnliche Geräte}
	\end{itemize}
	
	\subsection{TSG Stadtbergen Formular}
	\subsubsection{Beschreibung}
	%\paragraph
	Im Zuge eines Tennisturniers beim TSG Stadtbergen, sollte ein Formular erstellt werden, damit sich potentielle Mitglieder anmelden können. Technisch bedingt ist es performanter die Felder nicht durch PHP abzufragen, sondern bevor die Informationen zum Server zurückgeschickt werden, die Inputfelder zuerst direkt beim User auf Richtigkeit zu überprüfen. Diese Überprüfung findet mit Hilfe von JavaScript statt. Dies funktioniert natürlich nur, wenn JavaScript im Browser aktiviert ist.  
	
	%\paragraph
	Zuerst wurde recherchiert, welche Informationen überhaupt zu sammeln sind. Als wichtig haben sich folgenden Informationen herauskristallisiert. 
	
	\begin{itemize}
	\item{Der zu meldende Verein}
	\item{Die Gruppe in der sie spielen sollen}
	\item{nachdem es ein Doppel-Turnier ist, die Paarung mit jeweils Vor-, Nachname und Geburtsjahr}
	\item{die Spielklasse der Spieler}
	\item{Adressdaten des Ansprechpartners: z.B. Name, Telefonverbindung, Email, Wohnort usw.}
	\end{itemize}
	
	%\paragraph
	Zur Formularerstellung stellt HTML ein eigenes Formular-Tag zur Verfügung, um die Daten zu gruppieren. Das Formular wurde in drei Bereiche aufgeteilt, um die Informationen klar und übersichtlich darstellen zu können. Diese sind im Folgenden Informationen über die Spieler, Ihrer Spielklasse und Ansprechpartner.
	
	Der Spieler und der Ansprechpartnerbereich wurden mit Hilfe von Inputfeldern realisiert, die unter bzw. nebeneinander angeordnet worden sind. Beim mittleren Bereich für die Spielklasse, hat sich eine Radiobutton-Gruppe als optimal erwiesen, da jeweils nur eine Spielklasse auswählbar sein sollte. 
	Nachdem die Struktur des Formulars stand, wurde mittels Stylesheet-Informationen dessen Aussehen an den Webauftritt des Vereins angepasst. Eine Schwierigkeit bestand darin, dass bei verschiedenen Browsern die Felder anders aussehen und deshalb nicht sichergestellt ist, ob das Ergebnis auf jedem Browser gleich ist. Die Eigenschaften der Felder wurden so überschrieben, dass sich in jedem Browser die gleiche Darstellung ergibt.  
	
	Nachdem das Design fertig gestellt war, mussten die Felder auf Richtigkeit überprüft werden, zumal gefordert war, dass die Geburtsjahre maximal aus vier Zeichen und Zahlen bestehen dürfen. Analog zu dieser Forderung wurden für verschiedene Inputfelder JavaScript-Funktionen erstellt, die den Inhalt prüfen können. Herauszustellen sind die Felder für die Emailadressse, die z.B. ein @-Zeichen erfordern bzw. die PLZ, bei der nur fünf Zahlen zugelassen sind.
	
	Die Überprüfung findet so statt, dass die JavaScript-Funktion \emph{OnSubmit()} beim Klicken auf den Submit-Button ausgelöst wird, die wiederum Funktionen anstößt, die die jeweiligen Inputfelder überprüfen. Ist diese Überprüfung positiv ausgefallen, werden die Daten dann mittels der \emph{mail()}-Funktion an den Veranstalter der Turniers vorformatiert per Email versandt.   
	
	\subsection{Implementierung in Typo3: Zimmerei Reiter}
	\subsubsection{Überblick}
	%\paragraph
	Im ersten Bericht wurde bereits erwähnt, wie das Aussehen der Seite erstellt worden ist bzw. welche Veränderungen an der HTML-Vorlage des Kundes gemacht wurde, um ein XHTML gerechtes Design zu erstellen. Was mit Hilfe der W3C-Validators\index{Validator} \footnote{http://validator.w3.org/} überprüfbar ist. Dabei sollte noch erwähnt werden, dass Typo3 Informationen bei der Ausgabe einbettet, die keine 100\%ige Validierung zulassen. Das Template an sich sollte aber standardkonform erstellt werden. 
	
	%\paragraph
	Nachdem die Struktur und das Design fertiggestellt war, folgte die Einbindung der HTML-Vorlage in Typo3. Dafür war es nötig auf eine dafür eingerichtete Serverumgebung Typo3\index{Typo3} zu installieren und zu konfigurieren. Folgende Schritte sind erfolgt, um den Webauftritt einbinden zu können. 
	\begin{itemize}
	\item{Installation des Typo3 Systems}
	\item{Installation verschiedener Plugins für Typo3 darunter „Templa-Voila“}
	\item{Einarbeitung in Workspaces bzw. Rechtevergabe des Systems zur Veröffentlichung von Inhalt}
	\end{itemize}
	
	\subsubsection{Installation und Sicherheit}
	%\paragraph
	Zuerst wurde die neueste Version (4.2.6) des Typo3 Systems auf den Server geladen und installiert. Mit Hilfe des Installations-Wizards ist es leicht möglich in wenigen Minuten das grundlegende System auf dem Server einzurichten. Dabei war die MySQL (5.0.26) Datenbank und PHP (5.2.5) bereits auf dem Server vorinstalliert. Als Server System wird Apache (2.2.3) unter Suse-Linux betrieben. In der Konfiguration Linux/Apache bekommt man die beste Kompatibilität, da das Typo3 System unter einer LAMP umgebung weiterentwickelt wird. Zwar werden weitere Datenbanken unterstützt, darunter PostgreSQL oder Oracle 8/9 sowie FireBird (1.5.2), jedoch können viele Extensions nicht damit betrieben werden.\footnote{Buch: Praxiswissen Typo3} 
	
	%\paragraph
	Um die Sicherheit zu gewährleisten, sind nach der Installation des System weitere Einstellungen vorzunehmen. Darunter sollte das Passwort für das Installtool geändert werden (joh316), welches allgemein bekannt ist. Ein \emph{Username} ist für das Installtool nicht nötig. Im Backend ist das \emph{Tool} unter \emph{Admin-Werkzeuge} oder direkt über den Browser über \url{www.meineWebsite.de/typo3/install/} zu finden. 
	
	Falls das Passwort verloren geht, ist es möglich über SSH oder FTP die Datei \emph{localconf.php} im typo3conf Ordner zu editieren. Folgende Zeile sollte dabei geändert werden \emph{\$TYPO3\_CONF\_VARS['BE']['installToolPassword']}, das Passwort sollte dabei als md5-Hash\footnote{http://lemats.net/tech/tools/md5-hash-erzeugen/} vorliegen. Es ist auch möglich das InstallTool vollkommen zu sperren, was gemacht werden sollte, wenn man mit den Grundeinstellungen zufrieden ist. Dies geschieht, wenn im Verzeichnis \emph{typo3conf} die Datei \emph{ENABLE\_INSTALL\_TOOL}\footnote{http://www.thomas-koetter.de/2007/03/27/enable\_install\_tool-hinweise-zur-verwendung-der-neuen-typo3-option/} gelöscht bzw. umbenannt wird.
	 
	Auch im Backend-Bereich sollte das Passwort und der Username für den Administrator geändert werden, da diese auch allgemein bekannt sind (admin:password). Nach dem Login im Backend-Bereich, können die Änderungen bequem unter \emph{Admin-Werkzeuge->Verwaltung} gemacht werden. Dabei erscheinen Admins als blaues Icon. Jetzt sollten beim Login im Backend-Bereich keine Warnungen mehr erscheinen und das System einigermaßen sicher sein. Auf keinen Fall ist es anzuraten, das Verzeichnis \emph{typo3} umzubenennen, da sich z.B. Extensions oder Funktionen darauf verlassen, dass dieses Verzeichnis so benannt ist. 
	
	\subsubsection{Extensions}
	%\paragraph
	Im Typo3 Sprachgebrauch finden man oft das Wort \emph{Extension}\index{Extension}, dabei werden Erweiterungen gemeint, die installiert werden können, um das System um weitere Funktionalität aufzuwerten. Dabei werden meistens Backend-Extensions gemeint, Erweiterungen für das Frontend werden im Gegensatz als \emph{Plugins} bezeichnet. Typo3 ist erst seit der Version 3.5.x vollständig modular aufgebaut. In vorherigen Versionen waren News und Shopsysteme bereits intergriert. Mittlerweile werden nur grundsätzliche Erweiterung bei der Installation mitgeliefert.
	
	Weitere Extensions können mit Hilfe des Extension Managers einfach nachinstalliert werden. Heutzutage bietet das System eine API, die es ermöglicht Erweiterungen zu schreiben, was das System einen enormen Schritt nach vorne gebracht hat. Seitdem werden regelmäßig neue Extensions von freien Software Entwicklern geschrieben und damit Typo3 um viele Funktionen erweitert. Diese sollten bevor sie in einem Live-Systemen betrieben, erst getestet werden, da viele Erweiterungen nicht 100\%ig stabil laufen oder es Probleme mit anderen Extensions geben kann.\footnote{Buch: Praxiswissen Typo3 S.251}. Unter Typo3.net\footnote{http://www.typo3.net/faq/anfaengerfragen/welche\_extensions/} werden unter anderem folgende Extensions empfohlen.
	
	\begin{itemize}
	\item{\textbf{CSS styled content (css\_styled\_content)}}:
	Mit diesem Modul ist es möglich CSS-Templates zu benutzen, um das Design der Seite mit Hilfe von CSS zu gestalten
	\item{\textbf{TemplaVoila! (templavoila)}}: Das Modul ermöglicht visuell logische Abschnitte einer Designvorlage zu definieren
	\item{\textbf{AWStats (cc\_awstats)}}: AWStats erstellt aus Logdateien des Apache Webservers Visuelle Statistiken mittels HTML. Mit diesem Modul kann man AWStats vom Backend aus steuern
	\end{itemize}
	
	%\paragraph
	Die Installation kann vom Backend aus unter \emph{Admin-Werkzeuge->Erw-Manager} gesteuert werden. Hier ist es möglich nach Erweiterungen zu suchen, zu konfigurieren und zu installieren. Andere Sprachen können im Erweiterungsmanager, zu finden unter \emph{Translation Handling}, aktualisiert bzw. eingebunden werden. Die Sprache ist dann für jeden User unter \emph{Benutzerwerkzeuge->Einstellungen->Backendsprache} auf die gewünschte Sprache einstellbar. Die Sprachpakete sollten nach Möglichkeit frühzeitig auf die gewünschte Sprache umgestellt werden, Englisch ist dabei die Standardsprache des Systems. 
	
	\subsubsection{TemplaVoilá} 
	%\paragraph
	Templa-Voilá ist eine alternative Template-Engine, um Bereiche von HTML Vorlagen zur Strukturierung des Webauftritts auszuzeichnen, auch Mapping genannt. Diese Mapping-Informationen werden in XML-Dateien im Backend des Systems hinterlegt. Dazu gehören Bereiche, wie der Navigationsbereich, Footer oder Content in dem später der eigentliche Inhalt der Seite angezeigt wird.
	
	%\paragraph
	Nach der Installation des Moduls wird im Backend-Bereich unter \emph{Web} ein neues Modul mit dem Namen TemplaVoila\index{Templa Voilá} angezeigt. Das Seite-Modul wird durch die Installation von TemplaVoila ersetzt und erhält ein neues Icon. Allerdings können die mit der klassischen Methode angelegten Seiteninhalte nicht dargestellt werden, da benötigte Informationen für die Verarbeitung fehlen.
	
	Die bereits erstellte HTML-Vorlage, die mit Hilfe von CSS formatiert wurde, wird auf den Server in den Ordner \emph{Fileadmin->Templates} hoch geladen. Dabei muss der Ordner \emph{templates} vorher auf dem Server erstellt werden, da TemplaVoila nur aus diesem Ordner die Designvorlagen lesen kann. Nach diesem Schritt kann das sogenannte \emph{Mapping} stattfinden. Dafür ist es aber erforderlich eine neue Seite (root) und ein \emph{SysOrdner zu erstellen}. In den Eigenschaften der root-Seite sollte noch unter \emph{Allgemeine Datensatzsammlung}, der neu erstellte Sysordner eingebunden werden. Danach sollte unter \emph{Template->InfoModify (der Root-Seite)->Create Template for a new Site->Click here to edit whole template record->Enthält->Statische Templates einschließen} das CSS Styled Content eingebunden und die Einstellungen gespeichert werden.
	
	Es gibt zwei verschiedene Möglichkeiten die Vorlage für die neue Seite einzubinden. Dabei ist es möglich den Wizard zu starten, der unter TemplaVoila zur Verfügung steht oder in der Dateiliste ein Template auszuwählen und dieses direkt als Vorlage zu verwenden. In der zweiten Variante wird die gewünschte HTML-Vorlage gesucht und nach einem Klick auf das Icon wird aus dem Menü \emph{TemplaVoila} ausgewählt. So gelangt man ins \emph{Mapping}. Durch das Mapping werden visuell verschiedene Bereiche der Seite für verschiedene Aufgaben ausgewählt. Diese können z.B. die Navigation oder der Contentbereich sein. Dafür müssen verschiedene DIV- oder UL-Tags aus der HTML-Vorlage ausgewählt werden. Dabei ist es wichtig darauf zu achten, die Tags richtig zu mappen. 
	
	Bevor die Tags gesetzt werden, gibt es noch die Möglichkeit diese die Eigenschaften \emph{INNER} oder \emph{OUTER} mitzuteilen. Wenn die Markierung auf INNER gesetzt wird, wird später beim Einbinden von Inhalt der Bereiche innerhalb des Tags mit Inhalt gefüllt. Dabei bleibt der äußere Tag unberührt. Falls Outer ausgewählt wird, hat es auch Auswirkungen auf den z.B. umschließenden DIV-Tag. Dieser Tag wird ersatzlos gestrichen. Es besteht aber später noch die Möglichkeit, die mit Outer gekennzeichneten Tags z.B. per Typo-Script wieder einzubinden, bevor der Inhalt eingefügt wird. Der Vorteil liegt darin, dass man die eingebundene CSS-Klasse, dynamisch anpassen kann, um verschiedene Effekte zu erzielen. Es ist wichtig zu verstehen, wie diese Arten der Einbindung funktionieren, damit es keine Probleme mit dem Design bzw. der Einbindung von Content gibt.
	
	Als nächsten Schritt sollte man die gemachten Änderungen abspeichern. Dazu wird nach dem Mapping auf \emph{Save As} geklickt. Unter \emph{Title of DS/TO:} kann man seinem Mapping einen Titel geben und unter \emph{Store in PID:} wird der Storage-Folder, welches unter der Root-Seite eingebunden wurde ausgewählt. Wird der Storage Ordner nicht angezeigt, sollte man überprüfen, ob der Ordner auf der Root-Seite übernommen worden ist. Die so neu erstellten DS/TO Objekte sollten der Seite mitgeteilt werden. Dies geschieht unter \emph{Web->Seite->Seiteneigenschaften (der Root-Seite)->Erweitert}. Damit die Seite mit dem richtigen Style angezeigt wird, muss folgender Code unter \emph{Template->InfoModify(der Root-Seite)->Setup} eingebunden werden. 
	\lstset{
		language=HTML
	}
	
	\begin{lstlisting}
	#Default PAGE object:
	#page = PAGE
	#page.10 = TEXT
	#page.10.value = HELLO WORLD!
	
	page= PAGE
	page.typeNum=0
	#page.stylesheet= fileadmin/templates/ZimmereiReiter/css/main.css
	page.10=USER
	page.10.userFunc= tx_templavoila_pi1->main_page
	\end{lstlisting}
	
	Die Erklärungen zu dem Codeabschnitt wird unter \emph{TypoScript}\index{TypoScript} erklärt. Es sollte nicht vergessen werden, die Kopfteile der Seite, die nötig sind, auch auszuwählen. Sonst könnte es sein, dass das Stylesheet nicht eingebunden wird. Wird beim Aufruf der Seite eine Fehlermeldung ausgegeben, sollte man die Eigenschaften unter Erweitert überprüfen und die richtigen TemplaVoila Vorlagen auswählen. Damit neu erstellter Content auch angezeigt wird, sollte zusätzlich beim Mapping ein Elemtent ausgewählt und diesen im Feld \emph{Mapping Type}: als „Content Elements“ selektieren. Neu erstellter Content wird automatisch in diesem Feld angezeigt. 
	\subsubsection{TypoScript}
	%\paragraph
	TypoScript ist weder Programmiersprache noch Skriptsprache. Viel mehr kann man die \emph{TS} als Konfigurationssprache\footnote{http://www.fi-ausbilden.de/blog/2007/07/08/typoscript-eine-einfuhrung/} ähnlich der Windows Registry betrachten, die von Typo3 ausgewertet wird. Sie dient dazu bestimmte Eigenschaften zu definieren und so das Verhalten von Typo3 zu steuern. Mit der Sprache ist es z.B. möglich Navigationsstrukturen zu erstellen, Inhalt dynamisch auszugeben oder Sicherheitseinstellungen zu setzen.  
	
	%\paragraph
	Jeder Typo3 Auftritt muss vor der Darstellung von Content konfiguriert werden. Das wichtigste Objekt zur Konfiguration ist hierbei das \emph{PAGE}-Objekt. Diesem Objekt werden globale Eigenschaften mitgeteilt, die für den gesamten Auftritt gelten. Um das Page-Objekt anzusprechen muss man erst eine Instanz erzeugen (page=PAGE). Mit \emph{page.typeNum=0} wird das Standardaussehen der Seite definiert. Denkbar wäre es, eine alternative Ausgabe zu ermöglichen, die dann mittels folgendem Parameter \emph{type=\#} in der URL geändert werden kann. Dafür sollte aber die Styleangabe im Kopfbereich des Mappings ausgeschaltet sein, ansonsten wird immer nur dieser übernommen.
	
	Mit der \emph{page.Stylesheet} wird der Standardausgabe mitgeteilt, welches Stylesheet zur Ausgabe herangezogen werden soll. Schließlich wird mit \emph{page.10=USER} und \emph{page.10.userFunc= tx\_templavoila\_pi1->mail\_pagestyle} Typo3 mitgeteilt, dass die Funktion/Extension TemplaVoila benutzt wird, um die Anzeige zu erzeugen. Funktionen bzw. Extensions werden über das Objekt USER angesprochen. 
	
	Angenommen beim Mapping wurde ein Bereich als Navigation markiert und in den Eigenschaften Data Elements Editing Type:\emph{TypoScript Object Path} ausgesucht. Kann man diesen Bereich per TypoScript steuern. Um ein Menü zu erzeugen, welches sich an im Backend erstellten Seitenstruktur hält, kann man folgenden Code ergänzen.
	
	\lstset{
		language=HTML
	}
	\begin{lstlisting} 
	lib.mainMenu= HMENU
	lib.mainMenu{
		entryLevel = 0
		excludeUidList= 54, 57
		wrap= <ul id="top">|</ul>
		
		1 = TMENU
		1{
			#NO= 1
			NO{
				allWrap= <li><div>|</div></li>
			}
			
			CUR = 1
			CUR{
				allWrap= <li class="typoMainMenuCUR"><div>|</div></li>
			}
		}
	}
	\end{lstlisting}
	
	Dabei ist \emph{lib.mainMenu} der Name des TypoScript-Path, der diesem Objekt mitgegeben wurde. Dieser wird als Menu Container (HMENU) definiert und mit 1=TMENU, das erste Menu als Textmenu deklariert. Mit \emph{entryLevel=0} wird dem Menu mitgeteilt, dass nur die erste Ebene in der Seitenstruktur übernommen werden soll. Der vertikale Strich | ist eine Markierung für den Content, der eingebunden werden soll. Hier wird mittels \emph{Wrap}, das was im TMENU folgt mit dem ul-Tag umschlossen. Diesem wird mittels einer Stylesheet-Angabe eine Klasse mitgegeben. Dafür muss der Menu-Bereich als OUTER definiert worden sein, damit das Wrapping funktioniert bzw. der Tag nicht zweimal im HTML-Code der Seite erscheint. Mit \emph{excludeUidList=} ist es zusätzlich möglich bestimmte Seiten aus dem Seitenbaum explizit beim Anzeigen auszuschließen.  \emph{Allwrap} umschließt wiederum die Einzelnen Verknüpfungen bzw. Listeneinträge. Dabei können Zustände definiert werden, wie \emph{NO} für Normal oder \emph{ACT} für Aktiv, um davon abhängig die Listenelemente hervorzuheben oder nicht.
	
	Es gibt noch weitere Eigenschaften, die verändert werden können, an dieser Stelle wird auf die TypoScript-Referenz\footnote{http://www.typo3.net/tsref/} und an eine Einführung\footnote{http://www.fi-ausbilden.de/blog/2007/07/08/typoscript-eine-einfuhrung/} verwiesen.  
	
	
	\subsubsection{Benutzerfreundliche URLs (SEO)}
	Weitere Einstellungen bestanden darin, die URLs, die Typo3 erzeugt mittels „mod-Rewrite“ und Alias-Einstellungen für den User und Webrobots freundlicher zu gestalten. Dazu war es nötig eine \emph{.htaccess} Datei ins Hauptverzeichnis der Seite zu legen.
	
	\lstset{
		language=bash
	}
	
	\begin{lstlisting}
	RewriteEngine On
	RewriteCond %{REQUEST_FILENAME} !-f
	RewriteRule ^[^/]*\.html$ index.php
	\end{lstlisting}
	
	Damit Seiteneingaben im Browser, die nicht existieren und auf \emph{.html} enden, auf die \emph{index.php} Seite umgeleitet werden. Mit Hilfe von 
	
	\begin{lstlisting} 
	config.simulateStaticDocuments= 1
	config.simulateStaticDocuments_noTypeIfNoTitle=1
	\end{lstlisting}
	
	werden kryptische Darstellungen, wie \emph{index.php?id=123} vermieden, im Browser werden sie dann so \emph{123.html} dargestellt. Durch zusätzliche Alias-Einstellungen in den Seiteneigenschaften der jeweiligen Seiten, werden dann sprechende URLs, wie z.b. www.Zimmerei-reiter-gmbh.de/zimmerei.html erzeugt. 
	
	\subsubsection{Rechtevergabe und Workspaces}
	Als nächsten Punkt stand die Realisierung einer Konfiguration zur Veröffentlichung von Content\index{Content}. Dazu gibt es verschiedene Ansätze in Typo3. Diese sind z.B. über „Hidden Seiten“ zu bewerkstelligen, die nur von einem Redakteur freigegeben werden dürfen oder über Workspaces, die seit der Version 4.0 zur Verfügung stehen. Die Entscheidung fiel auf die modernere Variante, weil diese flexiblere Möglichkeiten bietet Content zu verwalten. 
	
	Im Normalfall stehen zwei Standard-Workspaces zur Bearbeitung von Content zur Verfügung (Live und Draft-WS). Wenn im Live-Workspace gearbeitet wird, wird jeder neu erstellte Inhalt, sofort im Webauftritt sichtbar. Dies ist aber nicht immer so gewollt, solange der Inhalt noch in der Entwurfsphase steht. Abhilfe schafft hier der Draft-Workspace, der als Spielwiese dient, aber keine weiteren Konfigurationsmöglichkeit bieten und z.B. keinen Zugang auf Dateien auf dem Server zur Verfügung stellt. Es ist nur erlaubt Objekte zu verändern, die auch in der Datenbank abgelegt werden können bzw. die durch Versionierung unterstützt werden. Neu erstellter Inhalt muss nach Fertigstellung durch einen Redakteur freigegeben werden. Dafür ist es notwendig, dass der Autor die neu erstellten Texte zur Revision an den Redakteur freigibt. Dieser wiederum kann die Veröffentlichung gewähren oder ablehnen und zusätzlich dem Ersteller eine Nachricht bzw. Kommentar zukommen lassen, falls etwas geändert werden soll. Nach Freigabe des Inhalts ist dieser dann im Live-Workspace zugänglich und somit im Frontend sichtbar.   
	
	Um den Usern trotzdem die Funktionalität zu bieten, auf Dateien (z.B. Bilder) zugreifen zu können, ist es möglich ein neues Workspace zu erstellen. In diesem kann auch die Möglichkeit gewährt werden, Dateien zu benutzen. Es sollte aber erwähnt werden, dass jede Änderung an Dateien im neuen Workspace eine sofortige Änderung im Live-Workspace mit sich bringt, da Typo3 noch keine Möglichkeit bietet Dateien zu versionieren.
	
	Durch geschickte Rechtevergabe ist ein flüssiger Workflow zur Erstellung von Content mit Hilfe von verschiedenen Workspaces leicht möglich und gut verwaltbar.  
	
	\section{Fazit}
	In den 20 Wochen Praktikum hab ich bei der Firma \emph{explido} einen sehr guten Überblick über Methoden und Werkzeuge zur Erstellung von Software und Webentwicklung erhalten. Es war sehr spannend an neuen Projekten mitzuwirken bzw. an bereits bestehenden weiter zu arbeiten.    
	
	\newpage
	
	\listoffigures
	\lstlistoflistings
	\printindex
	\end{document}  
	
	
}

