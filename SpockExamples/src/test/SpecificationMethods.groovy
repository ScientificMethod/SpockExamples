package test

import spock.lang.Shared
import spock.lang.Specification
import test.traits.TestFileParser

class SpecificationMethods extends Specification implements TestFileParser {
	@Shared
	String testData
	
	@Shared
	String fileName = 'myTextFile.txt'
	
	// setupSpec() is more commonly used for creating database connections
	//	and other things you only want to setup once
	def setupSpec() {
		testData = 'This is my string'
	}
	
	def setup(){ // Called by every Feature
		writeFile('', fileName, testData)
	}
	
	def cleanup(){ // Called by every Feature
		deleteFile(fileName)
	}
	
	def readFile(){
		expect: new File(fileName).text == testData
	}
}
