/*******************************************************************************
 * Copyright 2014 Alexander Leontyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.woodblockwithoutco.quickcontroldock.model.impl.buttons;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.woodblockwithoutco.quickcontroldock.model.buttons.BaseMediaButton;
import com.woodblockwithoutco.quickcontroldock.resource.Res;
//import com.woodblockwithoutco.remotecontroller.MediaCommand;

public class NextButton extends BaseMediaButton {

	public NextButton(Context context) {
		this(context, null, 0);
	}
	
	public NextButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public NextButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

//	@Override
	protected Drawable getMediaDrawable() {
		return Res.drawable.next_button;
	}

//	@Override
//	protected MediaCommand getMediaCommand() {
//		return MediaCommand.NEXT;
//	}

}
