# MVVM_State_Starter

MVVM Starter with states using Coroutines, Koin, Nav component, Paging2, Junit5, Github workflows.

## Structure characteristics and technologies used :-

1. Kotlin
2. Single-activity architecture, using [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
3. Dependency injection using [Koin](https://insert-koin.io/)
4. Async./Threading using [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
5. Remote API calls using [Retrofit2](https://github.com/square/retrofit)
6. Unit tests using [Junit5](https://www.baeldung.com/junit-5-kotlin), [Mockk](https://mockk.io/)
7. UI (Android) tests  using [Kaspresso](https://github.com/KasperskyLab/Kaspresso)(wrapper over [Kakao using Espresso](https://github.com/agoda-com/Kakao)) 

## Architecture

This project uses a MVVM structure with View States using [This Starter](https://github.com/khaled-ibtikar/mvvm_starter_koin_coroutines).


## What are View States ?
In a raw MVVM structure, usually `LiveData` is initialized in a `ViewModel` then observed on from it's `View` and it's value is changed based on `ViewModel`'s logic and reflected on the `View`.

* Simillary each ViewModel's LiveData objects are grouped as `States` in a `ViewState` sealed class, this class contains all possible screen/view specific states where the `ViewModel` can change the current `ViewState`.

* These `ViewStates` are handeled in `fun render()` in `View` to reflect changes on UI based on each state.

Why use `ViewStates` ?
  
   1. To combine boilerplate `LiveData` objects in a `ViewModel` for a specific sreen/view to one `LiveData` object that changes based on the current UI State
   2. Each `View` have it's own predefined possible UI states 
   3. Each `ViewState` class can extend a `BaseViewState` with UI states that are common across all screens that can be handled in a parent `View` class

## How to implement a new screen/feature ?

  1. Create a new package/folder in `ui/features`
  2. Create a new `Fragment` which extends from any of the Base Fragments in `ui/platform` (`Fragment/Dialog/BottomSheet/Paging`) that takes generic types of :-

     * `ViewLayoutBinding` i.e `fragment_example` -> `FragmentExampleBinding`
     * `ViewModel` that extends `BaseViewModel` ( pass `BaseViewModel` if not needed)
     * `ViewState` that extends `BaseViewState` ( pass `BaseViewState` if not needed)
  
  3. Create a new `ViewModel` which extends `BaseViewModel `and pass `ContextProviders` in constructor, add it's injection in `di/ViewModelsModule`
  4. Create a new `ViewState` which extends `BaseViewState` and has `data class`(s) and/or `object`(s) which extends this `ViewState`
  
## How to add remote data ?

  * Add a new API module in `data/remote/Apis` containing APIs that return `Response<BaseApiType<T>>`
      1. `BaseApiType` can either be `ApiBaseResponse` (default) or `ApiBaseResponseArrayOfData` (for pagination)
      2. `T` -> can either be a response (add a new response in `data/remote/responses`) or a direct Entity (add a new entity in `data/remote/entities`) or `*` as a star projection if not needed i.e `Response<BaseApiType<*>>`

## How to add local data ?  
  1. To add a Shared Preferences key -> Add a new enum value in `local/sharedprefs/SharedPrefsKeys`, set/get using `SharedPreferencesUtils.set/get(key)`

## How to use data ?  
  * Create a new (or use a current) `Repository` in `data/repositories` which extends `BaseRepository`
      1. Pass `ContextProviders` and `Apis` (if needed) and `SharedPreferencesUtils` (if needed) in constructor
      2. Add it's injection in `di/RepositoryModule`
  
  * Consume it by either :- 
    
    1. Passing it in `ViewModel`'s constructor
    2. Passing it in a `DataSourceFactory` that extends `BaseDataSourceFactory` that takes :-
        * `ContextProviders`
        * `ViewModel`'s `internalState`,
    
        then use this `DataSourceFactory` in a `BasePagingViewModel`
        
## How to add unit test ?
  1. Go to ViewModel to be tested, Press `CTRL+Shift+T` to create a new test class for that ViewModel, choose `test` package (not androidTest)
  2. Add `@ExtendWith(InstantExecutorExtension::class, KoinTestExtension::class)` to be able to use liveData and Koin
  3. Intialize Mocks
  4. Intialize UnitUnderTest(i.e ViewModel), make it observe on `BaseViewState`
  5. Add tests using `@Test` or `@ParameterizedTest`

## How to add UI (Android) test ?
  1. Kakao uses *Page Object Pattern*, So we add screen views to be tested in `screens` (if needed)
     * `ScreenClass` extends `Screen<ScreenClass>`
     * Add views using `KTextView{ }`, `KImageView{ }`, etc.. and include matchers inside them
  2. Add TestClass in `tests` that extends `TestCase()`, `KoinComponent`( to use Koin if needed)
     * Add test using `@Test` and using :-
    
                before{ }.after{ }.run{ step("Test Example"){ } }
     
     Note:- `before{}.after{}` can be ommited.
     
  3. For common scenarios in multiple tests add a new `Scenario` in `scenarios` that extends `Scenario()` and include in it common steps, asserts.
     * Use them in `TestsCase()` by using `scenario(SomeScenario())`
        
