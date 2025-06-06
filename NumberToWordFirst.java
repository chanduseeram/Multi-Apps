package com.example.utils;
public class NumberToWordFirst {

        @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
	public String test(int numberFirst) {
		StringBuilder output = new StringBuilder();
		try {
		String crore = "crore,";
		String hundred = "hundred";
		String tenArray[] = {"","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
		String array[] = {"zero","one","two","three", "four", "five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
		
		
		//Up to 9 values --- 999 hundred crore's 
		if (numberFirst>0 && numberFirst<=9)
		{
			output.append(array[numberFirst]+ " " + hundred);
		}
		
		//Up to 99 values --- 9999 thousand crores's
		else if (numberFirst>0 && numberFirst<=99)
		{
			
			int digit = numberFirst/10;
			if(numberFirst<=19) {
				output.append(array[numberFirst]+" thousand ");
				numberFirst = numberFirst%10;
				output.append(test(numberFirst));
			}
			else {
				output.append(array[digit]+" thousand ");
				numberFirst = numberFirst%10;
				output.append(test(numberFirst));
			}
		}
		
		
		//Up to 999 values --- 99999 thousand crores's
				else if (numberFirst>0 && numberFirst<=999)
				{
					int one = numberFirst/100;
					int digit = numberFirst/10;
					int digit2 = digit%10;
					
					int three = numberFirst%100;
					int digit3 = three%10;
					
					if (digit2==0 && digit3==0) {
							output.append(tenArray[one]+" thousand ");
					}
					
					
					else if(digit3==0) {
						if(digit<=19) {
							output.append(array[digit]+" thousand ");
						}
						else {
							output.append(tenArray[one]+" "+ array[digit2]+" thousand " + crore + " ");
						}
					}
					
					else {
						if(digit<=19) {
							output.append(array[digit]+" thousand ");
							numberFirst = digit3;
							output.append(test(numberFirst));
						}
						else {
							output.append(tenArray[one]+" "+ array[digit2]+" thousand ");
							numberFirst = digit3;
							output.append(test(numberFirst));
						}
					}
				}
		
		

		
}
		catch(Exception ex) {
			ex.getStackTrace();
			output.append("Please enter numberFirst only");
		}
		return output.toString();
	}

}
