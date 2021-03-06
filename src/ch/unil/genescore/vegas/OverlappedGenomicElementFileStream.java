/*******************************************************************************
 * Copyright (c) 2015 David Lamparter, Daniel Marbach
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *******************************************************************************/
package ch.unil.genescore.vegas;

public abstract class OverlappedGenomicElementFileStream {
	
	OverlappedGenomicElement currentElement_ = null;
	boolean streamInitialized_ = false;
	boolean streamOpen_= false;
	
	/** updates currentElement_ 
	 * @throws  */
	abstract protected void loadElement();
	
	public OverlappedGenomicElement getNextAsOverlappedGenomicElement(){	
		if (!streamOpen_)
			throw new RuntimeException("never use on closed stream");
			if (!streamInitialized_){//initial load
				
				loadElement();
				streamInitialized_=true;
			}
			//System.out.println(currentElement_.mainElement_.id_);
			OverlappedGenomicElement elementToBeReturned = currentElement_;
				loadElement();
			
			if (currentElement_!=null){
			//	System.out.println(currentElement_.mainElement_.id_);
				if (currentElement_.compareTo(elementToBeReturned)<0){
					System.out.println(currentElement_.mainElement_.id_);
					System.out.println(elementToBeReturned.mainElement_.id_);
					throw new RuntimeException("stream not sorted properly");
				}
			}	
			return  elementToBeReturned;
		
	}

}
