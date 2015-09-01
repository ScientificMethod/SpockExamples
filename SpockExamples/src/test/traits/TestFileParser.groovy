package test.traits

trait TestFileParser {
	List filesWritten = []
	
	void writeFile(String directory, String fileName, String fileText) {
		new File(directory + fileName).with{
			write fileText
			filesWritten << (directory + fileName)
		}
	}
	
	void deleteFile(String directory, String fileName) {
		new File(directory + fileName).delete
	}
	
	/**
	 * Surprise! You can define spec setup/cleanup methods in a trait.
	 * Sadly, you cannot place a Spock test inside a trait.
	 */
	def cleanupSpec() {
		// Ensure all written files were deleted
		filesWritten.findAll { new File(it).exists() }.each { it.delete() }
	}
}
