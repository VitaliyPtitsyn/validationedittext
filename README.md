### **ValidationEditText**[ ![Download](https://api.bintray.com/packages/vitaliyptitsyn/maven/validationedittext/images/download.svg) ](https://bintray.com/vitaliyptitsyn/maven/validationedittext/_latestVersion)
It`s a simple library to validate edit text with MVVM+data binding in wast and esasy solution.
That is not a custom view. Its just a bindingAdapter with instrumental classes to make validation.

### **Integration**
To add `ValidationEditText` to your project, first make sure in root `build.gradle` you have specified the following repository:
```groovy
    repositories {
        jcenter()
    }
```
>***Note***: by creating new project in Android Studio it will have `jcenter` repository specified by default, so you will not need to add it manually.

Once you make sure you have `jcenter` repository in your project, all you need to do is to add the following line in `dependencies` section of your project `build.gradle`.
 
See latest library version [ ![Download](https://api.bintray.com/packages/vitaliyptitsyn/maven/validationedittext/images/download.svg) ](https://bintray.com/vitaliyptitsyn/maven/validationedittext/_latestVersion)
```groovy
implementation 'com.github.vitaliyptitsyn:validationedittext:X.X.X'
```

### **Usage Sample**
Usage of `PageIndicatorView` is quite simple. Just like regular data binding events.
```xml
     <data>
            <import type="com.pvitaliy.validationtext.rules.ContentValidation" />
        </data>

        <com.google.android.material.textfield.TextInputEditText
                  android:id="@+id/et_name"
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:drawableStart="@drawable/ic_person"
                  android:imeOptions="actionNext"
                  android:inputType="textPersonName"
                  app:VET_show_error_mode="@{vm.liveShowOnEdit}"
                  app:VET_validation_content="@{ContentValidation.NOT_EMPTY}"
                  app:VET_validation_result="@={vm.liveName}"
                  tools:text="18,6" />
```
In binding `bm.liveName` live data with `ValidateResult` class 

```kotlin
data class ValidateResult(
    val originalText: String,
    val validatedText: String = "", // empty if text is invalid 
    val isValid: Boolean = false
)
```
To send text to validation exit text need to predefined "originalText" in ValidateResult classes instants, in you default Vm.

In case when you want to use just text, you need use "VET_validation_text"  instead of "VET_validation_result"

Also "VET_show_error_mode" used to control when to show validated text

Name| Behavior
---- | -------------- 
`ErrorMode.None`| Do not show error reason on edit Text
`ErrorMode.OnUserInput`| Show only on user focused view and typing
`ErrorMode.Always`| always validate result (Event if you send value in runtime) 
`ErrorMode.Once`| Most interested format. Validate text once, than set mode accordingly to `nextMode`




### **License**

    Copyright 2019 Vitaliy Ptitsyn
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

