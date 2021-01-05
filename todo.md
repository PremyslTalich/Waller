# Todo
- Add BackgroundAlerts etc. to collection detail screen
- Add "no more photos/collections" message for the user (at the end of pagination)
- Loading HQ photo on photo detail (maybe user blurhash?)
- Add auto open/close keyboard when opening/closing the search dialog
- Add clickable tags on photo detail info card, so user can easily search by tags on photo
- Add context menu on collection detail screen with options like "use this collection for autowaller etc."
- Gradle to kotlin

# Done
- Add proper splash screen
- Add loading notification fot the user
    - Loading more photos/collections
- Remove clickable location on photo detail card when no gps location is available
    - Changed the behaviour instead
- Add "safe calling" the REST APIs and some kind of "result" for the UCs... now it just crashes when the user has no internet...
    - Done through exceptions... as it should be
- Add "no internet" message for the user (no more crashes hopefully...)
- Add "empty search result" message for the user
- Add location info to photo detail info card
- Hide status bar on photo detail screen
- Add "photo count" to collection card on collections fragment
