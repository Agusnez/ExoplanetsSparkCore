// Compute the Earth Similaruty Index (ESI)

// position(19) * 11.209 => Planet radius in Eu (Earth Units)
// SQRT ( position(23)/position(19) * 317.83 * 1/11.209 ) => Ratio Earth Escape Velocity
// ¿? => Density
// position(42) => Effective Temperature
// position(32) => Stellar Flux in Eu (Earth Units)

// ESI Method 1
var i = 0

var xi = Array(1.0,1.0,1.0,1.0)      // Values of Exoplanet
var xi0 = Array(1.0,1.0,1.0,1.0)     // Values of Earth
var wi = Array(0.57,1.07,0.70,5.58)  // Weights of each feature
var esi = 1.0

for( i <- 0 to 3){
  esi *= scala.math.pow((1.0 - scala.math.abs((xi(i)-xi0(i))/(xi(i)+xi0(i)))),(wi(i)/4.0))

}

println("ESI = " + esi)

// ESI Method for exoplanets
val s = 5.0  // Stelar Flux of exoplanet (Eu)
val se = 1.0 // Stellar Flux of Earth (Eu)

val r = 2.0  // Radius of exoplanet (Eu)
val re = 1.0 // Radius of Earth (Eu)

var esiExoplanets = 1.0 - scala.math.sqrt(
  0.5 *
    (scala.math.pow((s-se)/(s+se),2) + scala.math.pow((r-re)/(r+re),2))
)

println("Exoplanet ESI = " + esiExoplanets)
