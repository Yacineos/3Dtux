<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:d="http://myGame/tux"
                version="1.0">
    <xsl:output method="html"/>
    
    
    <!-- on veut afficher un tableau qui prends: 
         - dans la premiere, cas le nom du joueur
         - dans la seconde, le lien du fichier .jpg de son avatar
         - dans la quatrieme, un tableau qui réference ses parties
        dans ce dernier tableau, nous aurons la date, si le joueur a trouvé le mot,
        le temps, le mot et son niveau      
    -->
    <xsl:template match="/">
        <html>
            <head>
            </head>
            <body>
                <h1>Profil</h1>
                <table>
                    <xsl:apply-templates select="//d:profil"/>
                </table>
                
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="d:profil">
        <tr>
                        <td><h2><xsl:value-of select="d:nom/text()"/></h2></td>
        </tr>
        <tr>                <td><h3><xsl:value-of select="d:avatar/text()"/></h3></td> </tr>
        <tr>                <td>
                            <table>
                                
                                    <xsl:apply-templates select="d:parties/d:partie"/>
                                
                            </table>
                        </td>
        </tr>
        
        
    </xsl:template>
    
    
    
    <xsl:template match="d:partie">
        <tr>
            <td>temps : <xsl:value-of select="./d:temps/text()"/></td> 
        </tr>
        <tr>
            <td>mot recherché : <xsl:value-of select="./d:mot/text()"/></td>
        </tr>
        <tr>
            <td>date : <xsl:value-of select="@date" /></td>
        </tr>
        <tr>    
            <td>niveau : <xsl:value-of select="d:niveau/text()" /></td>
        </tr>
        <tr/>
        <tr/>
        <tr/>
    </xsl:template>

</xsl:stylesheet>
