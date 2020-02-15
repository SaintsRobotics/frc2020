# Strategies

## Shoot all balls

```mermaid
stateDiagram

start:move to directly in front
shoot: shoot all available balls

[*]-->start
start-->shoot
shoot-->[*]
```

## Move off line
```mermaid
stateDiagram
moveBackward: Move back off line 1 meter

[*]-->moveBackward
moveBackward-->[*]
```
## Pickup far rendezvous balls
```mermaid
stateDiagram

moveToFarRendezvousBalls: move to location of far rendezvous Balls
pickupFarRendezvousBalls: pickup two balls from far rendezvous

[*]-->moveToFarRendezvousBalls
moveToFarRendezvousBalls-->pickupFarRendezvousBalls
pickupFarRendezvousBalls-->[*]
```
## Pickup near rendezvous balls
```mermaid
stateDiagram

moveToNearRendezvousBalls: move to location of near rendezvous Balls
pickupNearRendezvousBalls: pickup three balls from near rendezvous

[*]-->moveToNearRendezvousBalls
moveToNearRendezvousBalls-->pickupNearRendezvousBalls
pickupNearRendezvousBalls-->[*]
```
## Pickup trench balls
```mermaid
stateDiagram

moveToTrenchBalls: move to location of trench balls
pickupTrenchBalls: pickup balls from trench

[*]-->moveToTrenchBalls
moveToTrenchBalls-->pickupTrenchBalls
pickupTrenchBalls-->[*]
```
## Near to far rendezvous transition
```mermaid
stateDiagram
moveFromNearToFar: move to location of near rendezvous balls from near rendezvous balls

[*]-->moveFromNearToFar
moveFromNearToFar-->[*]
note left of moveFromNearToFar
            The near rendezvous
            is the rendezvous
            always on the right 
            side when viewed
            from the bots perspective
            it always have three balls
            at the start of each round
        end note
```
## Far to near rendezvous transition
```mermaid
stateDiagram
moveFromFarToNear: move to location of near rendezvous balls from far rendezvous balls

[*]-->moveFromFarToNear
moveFromFarToNear-->[*]
note left of moveFromFarToNear
            The near rendezvous
            is the rendezvous
            always on the right 
            side when viewed
            from the bots perspective
            it always have three balls
            at the start of each round
        end note
```

## Go for 23!
```mermaid
stateDiagram

startStrategy: [strategy] Shoot all balls
moveBackwards: [strategy] move off line

[*]-->startStrategy
startStrategy-->moveBackwards
moveBackwards-->[*]
```
## Go for 23 + 30 = 53pts!
```mermaid
stateDiagram

startStrategy: [strategy] Go for 23!
pickupFarRendezvousBalls: [strategy] Pickup far rendezvous balls
moveFromFarToNear: [strategy] Move from far to near rendezvous
pickupNearRendezvousBalls: [strategy] Pickup near rendezvous balls
shootStrategy: [strategy] Shoot all balls


[*]-->startStrategy
startStrategy-->pickupFarRendezvousBalls
pickupFarRendezvousBalls--> moveFromFarToNear
moveFromFarToNear--> pickupNearRendezvousBalls
pickupNearRendezvousBalls-->shootStrategy
shootStrategy-->[*]
```
## Go for 23 + 18 = 41pts!
```mermaid
stateDiagram

startStrategy: [strategy] Go for 23!
pickupTrenchBalls: [strategy] Pickup trench balls
shootStrategy: [strategy] Shoot all balls

[*]-->startStrategy
startStrategy-->pickupTrenchBalls
pickupTrenchBalls-->shootStrategy
shootStrategy-->[*]
```
