package com.example.aitripdemo

val defaultPrompt =
    """
        I have %s adult %s child %s infant, will flight from %s city to %s city with ticket class %s 
        The trip length from %s to %s.
        You are a travel agency. Please help me to create 2 trip plans at %s with above information.
        The response return will format like this:
      {
        "tripName": "Da Nang Beach & Culture Adventure",
        "duration": "5 days",
        "theme": "Relaxation, Culture, and Coastal Exploration",
        "places": [
            "My Khe Beach",
            "Han River Night Market",
            "Marble Mountains",
            "Son Tra Peninsula",
            "Linh Ung Pagoda",
            "Monkey Mountain", 
            "Ban Co Peak",
            "Ba Na Hills",
            "Hoi An Ancient Town",
            "Cham Museum",
        ],
        "activities": [
          "Day 1: Arrival in Da Nang, check-in to beach resort, relax by My Khe Beach, explore Han River Night Market for local street food.",
          "Day 2: Morning swim at My Khe Beach, visit Marble Mountains (caves, pagodas, viewpoints), explore Son Tra Peninsula (Linh Ung Pagoda, Monkey Mountain, Ban Co Peak for panoramic views).",
          "Day 3: Ba Na Hills day trip (Golden Bridge, French Village, Fantasy Park), enjoy cable car ride, return for seafood dinner by the beach.",
          "Day 4: Day trip to Hoi An Ancient Town: Japanese Covered Bridge, lantern-lit streets, visit local tailor shops, boat ride along Thu Bon River, try local specialties in Hoi An Market.",
          "Day 5: Han Market shopping, visit Cham Museum (ancient Cham artifacts), relax at Dragon Bridge area (fire/water show on weekends), departure."
        ],
        "foodSuggestions": [
          "Mi Quang (Quang noodles)",
          "Cao Lau (Hoi An noodles)",
          "Banh Xeo (Vietnamese pancake)",
          "Bun Thit Nuong (grilled pork with vermicelli)",
          "Bun Cha Ca (fishcake noodle soup)",
          "Banh Mi (Vietnamese baguette sandwich)",
          "Seafood (grilled oysters, steamed clams, garlic butter prawns, tamarind crab)",
          "Banh Canh Cua (thick noodle soup with crab)",
          "Nem Lui (lemongrass skewers with peanut sauce)",
          "Avocado ice cream (a famous Da Nang dessert)"
        ],
        "accommodations": [
            "Hyatt Regency Danang Resort and Spa",
            "Pullman Danang Beach Resort",
            "Furama Resort Danang"
        ]
      }

       The duration = departure date - return date, (duration must be min is 3 days and max is 5 days)
       In the activities list, each day has at least 2 activities per day
       In the food suggestions list has as many as possible
       In the accommodations list has as many as possible
        """.trimIndent()