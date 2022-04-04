;--------------------------------
;Incluimos el Modern UI

  !include "MUI2.nsh"

;--------------------------------
;Propiedades de la interfaz

  !define MUI_ABORTWARNING
  !define NOMBREAPP "Agenda"

;--------------------------------
#General

;Nombre de la aplicación y del ejecutable
   Name "${NOMBREAPP}"
   Icon "agenda.ico"
   OutFile "Agenda.exe"

;Directorio de instalación
   DirText "Elija un directorio donde instalar la aplicación:"
   InstallDir "$PROGRAMFILES\${NOMBREAPP}"

;Obtenemos el directorio del registro (si esta disponible)
   InstallDirRegKey HKCU "Software\Agenda" ""
  
;Indicamos que cuando la instalación se complete no se cierre el instalador automáticamente
   AutoCloseWindow false

;Si se encuentran archivos existentes se sobreescriben
   SetOverwrite on
   SetDatablockOptimize on

;--------------------------------
#Paginas
;páginas referentes al instalador
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_INSTFILES

;páginas referentes al desinstalador
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES

;--------------------------------
#Lenguajes
;Definimos el idioma del instalador
  !insertmacro MUI_LANGUAGE "Spanish"

;--------------------------------

#Secciones

Section "Agenda" agenda

  SetOutPath "$INSTDIR"
;Hacemos que esta seccion tenga que instalarse obligatoriamente
  SectionIn RO 

;Incluimos todos los archivos que componen nuestra aplicación

  ;Archivos a instalar (solo archivos, los ejecutables van en la sección "prerequisitos"
  File agenda.jar
  File "agenda.ico"

;Menu inicio
  SetShellVarContext all
  createDirectory "$SMPROGRAMS\${NOMBREAPP}"
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\${NOMBREAPP}.lnk" "$INSTDIR\Agenda.jar" "" "$INSTDIR\agenda.ico"
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\Manual.lnk" "$INSTDIR\manual.pdf" "" ""
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\Desinstalar.lnk" "$INSTDIR\Uninstall.exe" "" ""
    
;Acceso directo en el escritorio
  CreateShortCut "$DESKTOP\${NOMBREAPP}.lnk" "$INSTDIR\${NOMBREAPP}.jar" "" "$INSTDIR\agenda.ico"
  
;Hacemos que la instalación se realice para todos los usuarios del sistema
  SetShellVarContext all

;Guardamos un registro de la carpeta instalada
  WriteRegStr HKCU "Software\Agenda" "" $INSTDIR
  
;Creamos un desintalador
  WriteUninstaller "$INSTDIR\Uninstall.exe"
SectionEnd


#Seccion desinstalador

Section "Uninstall"

SetShellVarContext all

;Borramos el ejecutable del menú inicio
  delete "$SMPROGRAMS\${NOMBREAPP}\${NOMBREAPP}.lnk"
  delete "$SMPROGRAMS\${NOMBREAPP}\Manual.lnk"
  delete "$SMPROGRAMS\${NOMBREAPP}\Desinstalar.lnk"

;Borramos el acceso directo del escritorio
  delete "$DESKTOP\${NOMBREAPP}.lnk"

;Intentamos borrar el menú inicio (Solo se puede hacer si la carpeta está vacío)
  rmDir "$SMPROGRAMS\${NOMBREAPP}"
 
;Archivos a desinstalar
    delete $INSTDIR\agenda.jar
    delete $INSTDIR\manual.pdf
    delete $INSTDIR\mysql-installer-community-8.0.28.0.msi
    delete $INSTDIR\jdk-8u321-windows-x64.exe
    delete $INSTDIR\agenda.ico
 
;Borramos el desinstalador
  delete $INSTDIR\Uninstall.exe
 
;Intentamos borrar la carpeta de instalación (Solo se puede si está vacía)
  rmDir $INSTDIR

  DeleteRegKey /ifempty HKCU "Agenda"

SectionEnd


#Seccion Prerequisitos, ejecucion de otros instaladores 

Section "Prerequisitos" prerequisitos

SectionIn RO

DetailPrint "Comenzando la instalacion de Mysql Server"     
    File "mysql-installer-community-8.0.28.0.msi"
  ExecWait 'msiexec /i mysql-installer-community-8.0.28.0.msi /passive CONSOLEARGS="install -type=Full -silent"'
 
 ExecWait "$\"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe$\" -i -q ServiceName=MySQL RootPassword=root ServerType=DEVELOPMENT DatabaseType=MYISAM Port=3306 RootCurrentPassword=root"




DetailPrint "Comenzando la instalacion de Java"     
    File "jdk-8u321-windows-x64.exe"
     ExecWait "$INSTDIR\jdk-8u321-windows-x64.exe"
	

DetailPrint "Creating database"
    ExecWait '"C:\Program Files\MySQL\MySQL Server 5.0\bin\mysql" --user=root --password=root --execute="CREATE DATABASE agenda;' $1 

SectionEnd  


#Seccion "Manual de usuario"

Section "Manual de usuario" manual

SetOutPath "$INSTDIR"

;Archivos a instalar
  File manual.pdf

SectionEnd

;--------------------------------
#Descripciones

  ;Descripcion de Agenda
  LangString DESC_Agenda ${LANG_SPANISH} "Archivos necesarios para la ejecución de la Agenda"

  ;Descripcion de Prerequisitos
  LangString DESC_Prerequisitos ${LANG_SPANISH} "Archivos necesarios para que Agenda funcione correctamente"

  ;Descripcion de Manual
  LangString DESC_Manual ${LANG_SPANISH} "Manual de usuario"

  ;Asignamos las descripciones a cada seccion
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${Agenda} $(DESC_Agenda)
    !insertmacro MUI_DESCRIPTION_TEXT ${Prerequisitos} $(DESC_Prerequisitos)
    !insertmacro MUI_DESCRIPTION_TEXT ${Manual} $(DESC_Manual)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END

;--------------------------------