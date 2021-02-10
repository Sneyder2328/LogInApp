/*
 * Copyright (C) 2018 Sneyder Angulo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sneyder.loginapp.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.sneyder.loginapp.di.ViewModelKey;
import com.sneyder.loginapp.ui.login.LogInViewModel;
import com.sneyder.loginapp.ui.main.MainViewModel;
import com.sneyder.loginapp.utils.ViewModelProviderFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@SuppressWarnings(value = "unused")
@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel.class)
  abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(LogInViewModel.class)
  abstract ViewModel bindLogInViewModel(LogInViewModel logInViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

}
