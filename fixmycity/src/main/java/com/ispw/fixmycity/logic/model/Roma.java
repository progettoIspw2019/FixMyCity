package com.ispw.fixmycity.logic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Roma implements City {

	private List<String> companiesCategories;
	private List<String> communityCategories;

	public Roma() {
		companiesCategories = new ArrayList<>();
		companiesCategories.add("Public transport");
		companiesCategories.add("Damaged street");
		companiesCategories.add("Abandoned building");
		companiesCategories.add("Security problem");

		communityCategories = new ArrayList<>();
		communityCategories.add("Garbage");
		communityCategories.add("Vandalism");
		communityCategories.add("Rubbish dump");
	}
	
	@Override
	public List<String> getCompaniesCategories() {
		return companiesCategories;
	}

	@Override
	public List<String> getCommunityCategories() {
		return communityCategories;
	}

	@Override
	public Double getLatitude() {
		return 41.902782;
	}

	@Override
	public Double getLongitude() {
		return 12.496366;
	}

	@Override
	public List<String> getAllCategories() {

		List<String> categories = new ArrayList<>();
		categories.addAll(companiesCategories);
		categories.addAll(communityCategories);
		Collections.sort(categories);

		return categories;
	}

	@Override
	public boolean isForCompany(String cat) {
		return companiesCategories.contains(cat);
	}

	@Override
	public boolean isForCommunity(String cat) {
		return communityCategories.contains(cat);
	}

	@Override
	public Double[][] getBorderShape() {

		return new Double[][] { { 41.73852846935917, 12.236709594726562 }, { 41.71546774903097, 12.311553955078125 },
				{ 41.68829673005491, 12.363739013671875 }, { 41.65341898632251, 12.41455078125 },
				{ 41.679066225164114, 12.453689575195312 }, { 41.6872711837914, 12.473602294921873 },
				{ 41.69034777353792, 12.496948242187498 }, { 41.68470724661066, 12.510681152343748 },
				{ 41.69496238228255, 12.527160644531248 }, { 41.697013213237994, 12.541580200195312 },
				{ 41.70982942509964, 12.55462646484375 }, { 41.71034202043942, 12.569046020507812 },
				{ 41.68932225997044, 12.581405639648438 }, { 41.68419444691368, 12.591705322265625 },
				{ 41.692398751628396, 12.60406494140625 }, { 41.70008933705676, 12.606124877929686 },
				{ 41.7041906065988, 12.6123046875 }, { 41.71085461169185, 12.605438232421875 },
				{ 41.724180549563606, 12.597885131835938 }, { 41.734941789858006, 12.59307861328125 },
				{ 41.74160260664948, 12.601318359374998 }, { 41.759019938155404, 12.588272094726562 },
				{ 41.769775340843786, 12.58277893066406 }, { 41.78667304519471, 12.58277893066406 },
				{ 41.80049512790971, 12.580032348632812 }, { 41.81329069491423, 12.591705322265625 },
				{ 41.80305444575587, 12.604751586914062 }, { 41.802542590364354, 12.619857788085936 },
				{ 41.789232915019845, 12.643890380859375 }, { 41.79640000673747, 12.644577026367188 },
				{ 41.808684585195934, 12.630844116210938 }, { 41.81943165932009, 12.627410888671875 },
				{ 41.81431422987254, 12.64251708984375 }, { 41.812778921301515, 12.656936645507812 },
				{ 41.81687299570986, 12.666549682617186 }, { 41.82813035092223, 12.632217407226562 },
				{ 41.833246676287196, 12.637710571289062 }, { 41.83120019521289, 12.647323608398438 },
				{ 41.851662061565385, 12.657623291015625 }, { 41.857287927691345, 12.67341613769531 },
				{ 41.84654720840246, 12.692642211914062 }, { 41.83733944214672, 12.737960815429688 },
				{ 41.83785101947692, 12.755813598632812 }, { 41.85779934552825, 12.73040771484375 },
				{ 41.86342467181004, 12.709808349609375 }, { 41.88387623204765, 12.696762084960936 },
				{ 41.89205502378826, 12.700881958007812 }, { 41.88898809959183, 12.737274169921875 },
				{ 41.90432124806034, 12.7716064453125 }, { 41.875696393231, 12.808685302734375 },
				{ 41.87671893034394, 12.85400390625 }, { 41.9155632172071, 12.766799926757812 },
				{ 41.929868314485795, 12.746200561523438 }, { 41.93548729665268, 12.732467651367188 },
				{ 41.931911638000784, 12.724227905273438 }, { 41.939062754848564, 12.688522338867188 },
				{ 41.930379151500844, 12.661743164062498 }, { 41.939062754848564, 12.64251708984375 },
				{ 41.95591578624303, 12.634963989257812 }, { 41.97021183411508, 12.610244750976562 },
				{ 41.98807738309159, 12.619857788085936 }, { 42.001855953248615, 12.601318359374998 },
				{ 42.00695837037897, 12.608871459960938 }, { 42.01665183556825, 12.610244750976562 },
				{ 42.020732852644294, 12.615737915039062 }, { 42.03348434294521, 12.617111206054688 },
				{ 42.03603433407788, 12.599945068359375 }, { 42.05031239367961, 12.5848388671875 },
				{ 42.05643057984999, 12.573165893554688 }, { 42.05388140723482, 12.541580200195312 },
				{ 42.06050904321049, 12.512054443359375 }, { 42.070704055614634, 12.494888305664062 },
				{ 42.08497432249349, 12.481155395507812 }, { 42.07834924051728, 12.468795776367186 },
				{ 42.069174908123216, 12.471542358398438 }, { 42.064077483778185, 12.466735839843748 },
				{ 42.052351854553024, 12.424850463867186 }, { 42.042663832203154, 12.427597045898438 },
				{ 42.05897965014623, 12.387771606445312 }, { 42.03093424950211, 12.3870849609375 },
				{ 42.04878275505149, 12.377471923828125 }, { 42.08497432249349, 12.378158569335936 },
				{ 42.1104489601222, 12.36236572265625 }, { 42.11707068963613, 12.389144897460938 },
				{ 42.14049586965896, 12.392578125 }, { 42.14456892973155, 12.356185913085938 },
				{ 42.12369172732473, 12.345199584960938 }, { 42.1104489601222, 12.329406738281248 },
				{ 42.07682027721137, 12.325286865234375 }, { 42.0518419954737, 12.311553955078125 },
				{ 42.029404144327636, 12.298507690429688 }, { 42.019712622928495, 12.257308959960936 },
				{ 42.01767211439039, 12.2442626953125 }, { 42.004407212963585, 12.240142822265625 },
				{ 41.98501507352482, 12.242202758789062 }, { 41.984504674276074, 12.218170166015625 },
				{ 41.95795827518022, 12.220916748046875 }, { 41.94468092700416, 12.222976684570312 },
				{ 41.92680320648791, 12.269668579101562 }, { 41.92935747337991, 12.297821044921875 },
				{ 41.89818843043047, 12.267608642578125 }, { 41.88540983525773, 12.238082885742188 },
				{ 41.81073178596062, 12.317733764648436 }, { 41.790256834329455, 12.286834716796875 },
				{ 41.77694460766678, 12.275848388671873 }, { 41.75287318430239, 12.277908325195312 },
				{ 41.74262728637672, 12.228469848632812 }, { 41.73852846935917, 12.236709594726562 } };
	}

}