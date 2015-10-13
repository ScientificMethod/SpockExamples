package test

import spock.lang.Specification
import spock.lang.Unroll

class Parameterizations extends Specification {
	def smallestTest(){
		expect:
			1==1
	}
	
	def "Test names can have spaces"(){
		expect: 1==1
	}

	def "Use where to pass test data"(){
		expect: 1==myNumber
		where: myNumber = 1
	}

	def "You can static type your parameters (all or nothing)"(int myNumber){
		expect: 1==myNumber
		where: myNumber = 1
	}
	
	def " << and = are easy to confuse"(){
		expect: oneElementOfList instanceOf List
		where:	oneElementOfList = [2, 4, 6, 8]
	}
	
	def " << and = are easy to confuse (continued)"(){
		expect: oneElementOfList instanceOf Number
		where:	oneElementOfList << [2, 4, 6, 8]
	}
	
		
	def "Simple iterative test"(Integer oneElementOfList){
		expect: 'the input to be even'
			oneElementOfList % 2 == 0 
		where:
			oneElementOfList << [2, 4, 6, 8]
	}
	
	@Unroll("Is #oneElementOfList even?")
	def "This name won't be shown"(Integer oneElementOfList){
		expect: 'the input to be even'
			oneElementOfList % 2 == 0
			
		where: 'my list is even'
			oneElementOfList << [2, 4, 6, 8]
	}
	
	def "Passing iterative and non-iterative"(Integer two, Integer myList){
		expect: 'the input to be odd'
			myList % two != 0
			
		where: 'my list is odd'
			two = 2
			myList << [3, 5, 7, 9]
	}
	
	def "Data can be passed in a table format"(Integer input, Integer output){
		expect: 'the input to be odd'
			input - 0 = output
			
		where: 'my input matches my output'
			input	| output
			1	| 1
			2	| 2
			3 	| 3
	}
	
	/**
	 * Times I use this where approach:
	 *  When my parameters are too long or plentiful for the table's horizontal format to work well
	 *  When my parameters make it difficult for the table to line up
	 *  When I want optional parameters
	 * 
	 *  Side note: @Unroll is talked about under the wiki's annotations section.
	 **/
	@Unroll("#iteration.name") 
	def "Passing a list of maps is useful for some tests"(Map iteration){
		expect: 'the input to be odd'
			iteration.input - iteration.subtr ?: 0 = output
			
		where: 'my input matches my output'
			iteration << [
			[name:"This is the name of my first test",
			input:1,
			output:1],
			
			[name:"This is what I will call my second test",
			input:2,
			subtr:1,
			output:1],
			]
	}
}
