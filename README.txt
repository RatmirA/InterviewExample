This is intended to be the start of a basic REST API backed by a local sqlite database.  It consists of two basic models, their associated service, and a basic unit test suite.  

All that should be required to run it is to install the latest eclipse (2020-09), the latest Java, and open the extracted folder from said Eclipse installation.  File > Open Projects From File System…

The project is set to use Java 14, but you're welcome to change that if you have a different version already installed.  The code should not require anything too recent.

You can execute the BaseTest test cases by pressing Ctrl-F11, or Debugging after setting breakpoints with F11.

BaseTest.testPostCount is SOMETIMES failing, while testGet always succeeds.  Your first task is to figure out why and fix it.

Your second task is to perform a code review for the services / models.  Add comments with your suggested changes.  No need to implement the code.
