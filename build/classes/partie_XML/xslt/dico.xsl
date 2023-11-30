<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
            xmlns:d ="http://myGame/tux"
>
    <xsl:output method="html"/>

   <!-- ON VEUT UNE FENETRE QUI CONTIENT LA LISTE DES DICTIONNAIRE TRIE PAR NIVEAU
        SI 2 MOT ONT LE MEME NIVEAU ON TRI PAR ORDRE ALPHABÉTIQUE ET LE TOUT SERA
        REPRÉSENTÉ SOUS FORMAT DE LISTE À PUCE 
    -->
    <xsl:template match="/">
        <html>
            <head>
            </head>
            <body>
                <title>Dictionnaire</title>
                <ul>
                    <xsl:apply-templates select="//d:mot">
                        <xsl:sort select="../@niveau" />
                        <xsl:sort select="./text()" /> 
                    </xsl:apply-templates>
                </ul>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="d:mot">
        <li>
            <xsl:value-of select="./text()"/>
        </li>
     </xsl:template>

</xsl:stylesheet>
