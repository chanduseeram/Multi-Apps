package com.example.utils;
public class NumberToWord {

        @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
	public String test(int number) {
		StringBuilder output = new StringBuilder();
		try {
		String crore = "crore,";
		String lakh = "lakh,";
		String thousand = "thousand,";
		String hundred = "hundred";
		String tenArray[] = {"","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
		String array[] = {"zero","one","two","three", "four", "five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
		
		
		//Up to two digits
		int startingValue = number/10;
		int remainder = number%10;
		
		//Up to three digits
		int hundredValue = number/100;
		int tensValue = startingValue%10;
		int storing_remainder = number%100;
		int three_remainder = storing_remainder%10;
		
		//Up to 4 digits
		int thousandValue = number/1000;
		int t_hundredValue = hundredValue%10;
		int t_tensValue = startingValue%10;
		int t_storing_remainder = number%1000;
		int t_storing2_remainder = t_storing_remainder%100;
		int four_remainder = t_storing2_remainder%10;
		
		
		//for noted large values
		if (number==100000) {
			output.append("one lakh"); 
			return output.toString();
		}
		if (number==1000000) {
			output.append("ten lakhs");
			return output.toString();
		}
		if (number==10000000) {
			output.append("one crore");
			return output.toString();
		}
		if (number==100000000) {
			output.append("ten crores");
			return output.toString();
		}
		if (number==1000000000) {
			output.append("one hundred crores");
			return output.toString();
		}
		
		
		//Up to 19 values
		if (number>=0 && number<=19)
		{
			output.append(array[number]);
		}
		
		//Up to 99 values
		else if (number>0 && number<=99)
		{
			output.append(tenArray[startingValue]+" "+array[remainder]);
		}
		
		//Up to 999 values
		else if (number>0 && number<=999)
		{
			int digit = number%100;
			if (tensValue==0 && remainder==0) {
				output.append(array[hundredValue]+" "+hundred);
			}
			else if (remainder==0) {
				output.append(array[hundredValue]+" "+hundred+" and "+tenArray[tensValue]);
			}
			else {
				if (digit<=19) 
					output.append(array[hundredValue]+" "+hundred+" and "+array[digit]);
				else	
					output.append(array[hundredValue]+" "+hundred+" and "+tenArray[tensValue]+" "+array[three_remainder]);
			}
		}
		
		//Up to 9999 values
		else if (number>0 && number<=9999)
		{
			int digit = number%100;
			if (t_hundredValue==0 && t_tensValue==0 && four_remainder==0)
				output.append(array[thousandValue]+" "+thousand);
			else if (t_tensValue==0 && four_remainder==0)
				output.append(array[thousandValue]+" "+thousand+" "+array[t_hundredValue]+" "+hundred);
			else if (four_remainder==0 && t_hundredValue==0) {
				int digitn = number%100;
				int digitnn = digitn/10;
				output.append(array[thousandValue]+" "+thousand+" and "+tenArray[digitnn]);
			}
			else if (t_hundredValue==0 && t_tensValue==0)
				output.append(array[thousandValue]+" "+thousand+" and "+array[four_remainder]);

			else if (four_remainder==0)
				output.append(array[thousandValue]+" "+thousand+" "+array[t_hundredValue]+" "+hundred+" and "+tenArray[t_tensValue]);
			else if (t_hundredValue==0) {
				if (digit<=19)
					output.append(array[thousandValue]+" "+thousand+" and "+array[digit]);
				else
					output.append(array[thousandValue]+" "+thousand+" and "+tenArray[t_tensValue]+" "+array[four_remainder]);
			}
			else if (t_tensValue==0)
				output.append(array[thousandValue]+" "+thousand+" "+array[t_hundredValue]+" "+hundred+" and "+array[four_remainder]);
			else {
				if (digit<=19)
					output.append(array[thousandValue]+" "+thousand+" "+array[t_hundredValue]+" "+hundred+" and "+array[digit]);
				else
					output.append(array[thousandValue]+" "+thousand+" "+array[t_hundredValue]+" "+hundred+" and "+tenArray[t_tensValue]+" "+array[four_remainder]);
			}
		}
		
		//Up to 5 numbers - 99 thousand 
				else if (number>=0 && number<=99999) {
					int digit = number/10000;
					int digit2 = number/1000;
					int digit3 = digit2%10;
					if(digit3==0) {
						output.append(tenArray[digit]+" "+ thousand +" and ");
					}
					else {
						output.append(tenArray[digit]+" ");
					}
					number = number%10000;
					if(number!=0) {
						output.append(test(number));
					}

				}
		
		//Up to 6 numbers - 9 lakh's 
		else if (number>=0 && number<=999999) {
			int digit = number/100000;
			output.append(array[digit]+" "+lakh+" ");
			number = number%100000;
			if(number!=0) {
				output.append(test(number));
			}

		}
		
		//Up to 7 numbers - 99 lakh's 
		else if (number>=0 && number<=9999999) {
			int digit = number/100000;
			int digit3 = digit/10;
			int digit2 = digit%10;
			if (digit<=19)
				output.append(array[digit]+" "+lakh+" ");
			else {
				if (digit2 == 0)
					output.append(tenArray[digit3]+" "+lakh+" ");
				else
					output.append(tenArray[digit3]+" "+array[digit2]+" "+lakh+" ");
			}
				
			number = number%100000;
			if(number!=0) {
				output.append(test(number));
			}

		}
		
		//Up to 8 numbers - 9 crore's 
		else if (number>=0 && number<=99999999) {
			int digit = number/10000000;
			output.append(array[digit]+" "+crore+" ");
			number = number%10000000;
			if(number!=0) {
				output.append(test(number));
			}

		}
		
		//Up to 9 numbers - 99 crore's 
		else if (number>=0 && number<=999999999) {
			int digit = number/10000000;
			int digit3 = digit/10;
			int digit2 = digit%10;
			if (digit<=19)
				output.append(array[digit]+" "+crore+" ");
			else {
				if (digit2==0)
					output.append(tenArray[digit3]+" "+crore+" ");
				else
					output.append(tenArray[digit3]+" "+array[digit2]+" "+crore+" ");
			}number = number%10000000;
			if(number!=0) {
				output.append(test(number));
			}

		}
		
		
		
		else {
			output.append("Not supported Up to this number");
		}
		}
		catch(Exception ex) {
			ex.getStackTrace();
			output.append("Please enter number only");
		}
		return output.toString();
	}

}
