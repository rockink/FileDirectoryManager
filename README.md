# FileDirectoryManager
FileManager for any JAVA app, especially for Web-app that saves, retrieves and deletes your file, plus gives you the address of that file so that you can store it on your database.

After importing the library, this is how you can use it.

    FileManager fileManager = new FileManager(pathToStoreConfigurationFile, pathToStoreData);
    String path = fileManager.getStreamForInsertion("nirmal".getBytes()); //it requires you to have byte[] 
    //path - is the local directory that it links to.
    

If the user doesn't want the file and therefore deletes it, you can acutally do this in this way.

    fileManager.delete(generatedLocalPathOfFile);

THATS ALL!! You are all set :)

What have I done?
It creates 5 tree of folders that each can comprise of 98 sub-folders, and these 98 subfolders until it reaches towards the tip have subfolders, that has the files stored. So, it can literally store- 

98*98*98*98*98 = 9039207968 files; // 9 BILLION FILES!! HASSLE FREE!! 


