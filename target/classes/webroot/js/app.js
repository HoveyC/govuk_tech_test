webix.ui({
    container: "listA",
    view: "grouplist",
    url: "rest-> ../candidates",
    width: 500,
    height: 400,
    templateBack: "#value#",
    templateGroup: "<b>Candidate:</b> #value#",
    templateItem: "<b>DOB:</b> #dob#  <b>Experience:</b> #experience#",
    select: true,
    scheme: {
        $group: {
            by: 'name'
        },
        $sort: {by: "value", dir: "desc"}
    }
});