@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  security-service startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and SECURITY_SERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\security-service-1.0-SNAPSHOT-plain.jar;%APP_HOME%\lib\spring-cloud-starter-netflix-eureka-client-4.0.0.jar;%APP_HOME%\lib\spring-boot-starter-data-mongodb-3.0.2.jar;%APP_HOME%\lib\spring-boot-starter-web-3.0.2.jar;%APP_HOME%\lib\lombok-1.18.24.jar;%APP_HOME%\lib\spring-boot-starter-oauth2-resource-server-3.0.2.jar;%APP_HOME%\lib\spring-boot-starter-actuator-3.0.2.jar;%APP_HOME%\lib\spring-boot-starter-json-3.0.2.jar;%APP_HOME%\lib\spring-boot-actuator-autoconfigure-3.0.2.jar;%APP_HOME%\lib\spring-cloud-netflix-eureka-client-4.0.0.jar;%APP_HOME%\lib\eureka-core-2.0.0.jar;%APP_HOME%\lib\eureka-client-2.0.0.jar;%APP_HOME%\lib\archaius-core-0.7.6.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.14.1.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.14.1.jar;%APP_HOME%\lib\jackson-databind-2.14.1.jar;%APP_HOME%\lib\jackson-annotations-2.14.1.jar;%APP_HOME%\lib\jackson-core-2.14.1.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.14.2.jar;%APP_HOME%\lib\spring-rabbit-3.0.4.jar;%APP_HOME%\lib\micrometer-registry-prometheus-1.11.0.jar;%APP_HOME%\lib\spring-cloud-starter-loadbalancer-4.0.0.jar;%APP_HOME%\lib\spring-cloud-starter-4.0.0.jar;%APP_HOME%\lib\spring-boot-starter-cache-3.0.2.jar;%APP_HOME%\lib\spring-boot-starter-3.0.2.jar;%APP_HOME%\lib\mongodb-driver-sync-4.8.2.jar;%APP_HOME%\lib\spring-data-mongodb-4.0.1.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-3.0.2.jar;%APP_HOME%\lib\spring-webmvc-6.0.4.jar;%APP_HOME%\lib\spring-security-oauth2-resource-server-6.0.1.jar;%APP_HOME%\lib\spring-security-oauth2-jose-6.0.1.jar;%APP_HOME%\lib\spring-security-oauth2-core-6.0.1.jar;%APP_HOME%\lib\spring-security-web-6.0.1.jar;%APP_HOME%\lib\spring-web-6.0.4.jar;%APP_HOME%\lib\spring-security-config-6.0.1.jar;%APP_HOME%\lib\spring-security-core-6.0.1.jar;%APP_HOME%\lib\micrometer-core-1.10.3.jar;%APP_HOME%\lib\micrometer-observation-1.10.3.jar;%APP_HOME%\lib\spring-amqp-3.0.1.jar;%APP_HOME%\lib\amqp-client-5.16.0.jar;%APP_HOME%\lib\spring-boot-autoconfigure-3.0.2.jar;%APP_HOME%\lib\spring-boot-actuator-3.0.2.jar;%APP_HOME%\lib\spring-boot-3.0.2.jar;%APP_HOME%\lib\spring-context-support-6.0.4.jar;%APP_HOME%\lib\spring-context-6.0.4.jar;%APP_HOME%\lib\spring-messaging-6.0.4.jar;%APP_HOME%\lib\spring-tx-6.0.4.jar;%APP_HOME%\lib\simpleclient_common-0.16.0.jar;%APP_HOME%\lib\spring-cloud-loadbalancer-4.0.0.jar;%APP_HOME%\lib\spring-cloud-context-4.0.0.jar;%APP_HOME%\lib\spring-cloud-commons-4.0.0.jar;%APP_HOME%\lib\spring-security-rsa-1.0.11.RELEASE.jar;%APP_HOME%\lib\httpclient5-5.1.4.jar;%APP_HOME%\lib\netflix-eventbus-0.3.0.jar;%APP_HOME%\lib\xstream-1.4.19.jar;%APP_HOME%\lib\jakarta.ws.rs-api-3.1.0.jar;%APP_HOME%\lib\jakarta.inject-api-2.0.1.jar;%APP_HOME%\lib\jakarta.annotation-api-2.1.1.jar;%APP_HOME%\lib\servo-core-0.12.21.jar;%APP_HOME%\lib\httpclient-4.5.14.jar;%APP_HOME%\lib\netflix-infix-0.3.0.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\commons-configuration-1.10.jar;%APP_HOME%\lib\compactmap-2.0.jar;%APP_HOME%\lib\jettison-1.4.0.jar;%APP_HOME%\lib\jakarta.servlet-api-6.0.0.jar;%APP_HOME%\lib\woodstox-core-6.2.1.jar;%APP_HOME%\lib\evictor-1.0.0.jar;%APP_HOME%\lib\spring-boot-starter-logging-3.0.2.jar;%APP_HOME%\lib\spring-data-commons-3.0.1.jar;%APP_HOME%\lib\spring-aop-6.0.4.jar;%APP_HOME%\lib\spring-beans-6.0.4.jar;%APP_HOME%\lib\spring-expression-6.0.4.jar;%APP_HOME%\lib\spring-core-6.0.4.jar;%APP_HOME%\lib\snakeyaml-1.33.jar;%APP_HOME%\lib\mongodb-driver-core-4.8.2.jar;%APP_HOME%\lib\bson-record-codec-4.8.2.jar;%APP_HOME%\lib\bson-4.8.2.jar;%APP_HOME%\lib\logback-classic-1.4.5.jar;%APP_HOME%\lib\log4j-to-slf4j-2.19.0.jar;%APP_HOME%\lib\jul-to-slf4j-2.0.6.jar;%APP_HOME%\lib\slf4j-api-2.0.6.jar;%APP_HOME%\lib\tomcat-embed-websocket-10.1.5.jar;%APP_HOME%\lib\tomcat-embed-core-10.1.5.jar;%APP_HOME%\lib\tomcat-embed-el-10.1.5.jar;%APP_HOME%\lib\spring-security-crypto-6.0.1.jar;%APP_HOME%\lib\nimbus-jose-jwt-9.24.4.jar;%APP_HOME%\lib\micrometer-commons-1.10.3.jar;%APP_HOME%\lib\HdrHistogram-2.1.12.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\spring-retry-2.0.0.jar;%APP_HOME%\lib\simpleclient-0.16.0.jar;%APP_HOME%\lib\bcpkix-jdk15on-1.69.jar;%APP_HOME%\lib\httpcore5-h2-5.1.5.jar;%APP_HOME%\lib\httpcore5-5.1.5.jar;%APP_HOME%\lib\commons-codec-1.15.jar;%APP_HOME%\lib\commons-math-2.2.jar;%APP_HOME%\lib\mxparser-1.2.2.jar;%APP_HOME%\lib\guava-19.0.jar;%APP_HOME%\lib\httpcore-4.4.16.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\dexx-collections-0.2.jar;%APP_HOME%\lib\stax2-api-4.2.1.jar;%APP_HOME%\lib\reactor-extra-3.5.0.jar;%APP_HOME%\lib\reactor-core-3.5.2.jar;%APP_HOME%\lib\spring-jcl-6.0.4.jar;%APP_HOME%\lib\jcip-annotations-1.0-1.jar;%APP_HOME%\lib\simpleclient_tracer_otel-0.16.0.jar;%APP_HOME%\lib\simpleclient_tracer_otel_agent-0.16.0.jar;%APP_HOME%\lib\bcutil-jdk15on-1.69.jar;%APP_HOME%\lib\bcprov-jdk15on-1.69.jar;%APP_HOME%\lib\commons-jxpath-1.3.jar;%APP_HOME%\lib\joda-time-2.3.jar;%APP_HOME%\lib\servlet-api-2.5.jar;%APP_HOME%\lib\antlr-runtime-3.4.jar;%APP_HOME%\lib\gson-2.9.1.jar;%APP_HOME%\lib\xmlpull-1.1.3.1.jar;%APP_HOME%\lib\reactive-streams-1.0.4.jar;%APP_HOME%\lib\logback-core-1.4.5.jar;%APP_HOME%\lib\log4j-api-2.19.0.jar;%APP_HOME%\lib\simpleclient_tracer_common-0.16.0.jar;%APP_HOME%\lib\stringtemplate-3.2.1.jar;%APP_HOME%\lib\antlr-2.7.7.jar


@rem Execute security-service
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SECURITY_SERVICE_OPTS%  -classpath "%CLASSPATH%" com.backend.securityservice.SecurityServiceApplication %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable SECURITY_SERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%SECURITY_SERVICE_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
