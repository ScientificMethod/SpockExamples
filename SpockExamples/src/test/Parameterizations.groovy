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
}
