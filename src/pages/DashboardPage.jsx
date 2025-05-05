import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '../components/ui/card';
import { Button } from '../components/ui/button';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '../components/ui/tabs';
import { BarChart3, Users, FolderKanban, Calendar, PlusCircle, CheckCircle2, Clock, GitBranch } from 'lucide-react';
import DashboardLayout from '../components/DashboardLayout';

function DashboardPage() {
  const [userRole, setUserRole] = useState("manager"); // For demo: manager, lead, project, general

  return (
    <DashboardLayout>
      <div className="flex flex-col gap-4 p-4 md:gap-8 md:p-8">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-3xl font-bold tracking-tight">Dashboard</h1>
            <p className="text-gray-500">Welcome back! Here's an overview of ML@SJSU projects and members.</p>
          </div>
          {(userRole === "manager" || userRole === "lead") && (
            <Link to="/projects/create">
              <Button className="bg-emerald-600 hover:bg-emerald-700">
                <PlusCircle className="mr-2 h-4 w-4" />
                New Project
              </Button>
            </Link>
          )}
        </div>

        <Tabs defaultValue="overview" className="space-y-4">
          <TabsList>
            <TabsTrigger value="overview">Overview</TabsTrigger>
            <TabsTrigger value="projects">Projects</TabsTrigger>
            <TabsTrigger value="members">Members</TabsTrigger>
            {userRole === "manager" && <TabsTrigger value="semesters">Semesters</TabsTrigger>}
          </TabsList>

          <TabsContent value="overview" className="space-y-4">
            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
              <Card>
                <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                  <CardTitle className="text-sm font-medium">Total Projects</CardTitle>
                  <FolderKanban className="h-4 w-4 text-muted-foreground" />
                </CardHeader>
                <CardContent>
                  <div className="text-2xl font-bold">12</div>
                  <p className="text-xs text-muted-foreground">+2 from last semester</p>
                </CardContent>
              </Card>
              <Card>
                <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                  <CardTitle className="text-sm font-medium">Active Members</CardTitle>
                  <Users className="h-4 w-4 text-muted-foreground" />
                </CardHeader>
                <CardContent>
                  <div className="text-2xl font-bold">45</div>
                  <p className="text-xs text-muted-foreground">+5 from last semester</p>
                </CardContent>
              </Card>
              <Card>
                <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                  <CardTitle className="text-sm font-medium">Project Members</CardTitle>
                  <CheckCircle2 className="h-4 w-4 text-muted-foreground" />
                </CardHeader>
                <CardContent>
                  <div className="text-2xl font-bold">24</div>
                  <p className="text-xs text-muted-foreground">53% of total members</p>
                </CardContent>
              </Card>
              <Card>
                <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                  <CardTitle className="text-sm font-medium">Current Semester</CardTitle>
                  <Calendar className="h-4 w-4 text-muted-foreground" />
                </CardHeader>
                <CardContent>
                  <div className="text-2xl font-bold">Spring 2025</div>
                  <p className="text-xs text-muted-foreground">Ends in 45 days</p>
                </CardContent>
              </Card>
            </div>

            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-7">
              <Card className="col-span-4">
                <CardHeader>
                  <CardTitle>Project Activity</CardTitle>
                </CardHeader>
                <CardContent>
                  <div className="h-[200px] flex items-center justify-center bg-gray-100 rounded-md">
                    <BarChart3 className="h-8 w-8 text-gray-400" />
                    <span className="ml-2 text-gray-500">Activity Chart</span>
                  </div>
                </CardContent>
              </Card>
              <Card className="col-span-3">
                <CardHeader>
                  <CardTitle>Recent Updates</CardTitle>
                  <CardDescription>Latest project and member activities</CardDescription>
                </CardHeader>
                <CardContent>
                  <div className="space-y-4">
                    <div className="flex items-start gap-4">
                      <Clock className="mt-0.5 h-5 w-5 text-gray-500" />
                      <div className="space-y-1">
                        <p className="text-sm font-medium leading-none">Computer Vision Project updated</p>
                        <p className="text-sm text-gray-500">2 new members joined • 2 hours ago</p>
                      </div>
                    </div>
                    <div className="flex items-start gap-4">
                      <GitBranch className="mt-0.5 h-5 w-5 text-gray-500" />
                      <div className="space-y-1">
                        <p className="text-sm font-medium leading-none">NLP Project repository created</p>
                        <p className="text-sm text-gray-500">By John Doe • 1 day ago</p>
                      </div>
                    </div>
                    <div className="flex items-start gap-4">
                      <Users className="mt-0.5 h-5 w-5 text-gray-500" />
                      <div className="space-y-1">
                        <p className="text-sm font-medium leading-none">5 new general members joined</p>
                        <p className="text-sm text-gray-500">Pending eligibility review • 3 days ago</p>
                      </div>
                    </div>
                  </div>
                </CardContent>
              </Card>
            </div>
          </TabsContent>

          <TabsContent value="projects" className="space-y-4">
            <Card>
              <CardHeader>
                <CardTitle>Current Semester Projects</CardTitle>
                <CardDescription>Spring 2025 active projects and their status</CardDescription>
              </CardHeader>
              <CardContent>
                <div className="space-y-4">
                  {[1, 2, 3, 4].map((i) => (
                    <div key={i} className="flex items-center justify-between border-b pb-4 last:border-0 last:pb-0">
                      <div className="space-y-1">
                        <div className="flex items-center gap-2">
                          <h4 className="font-medium">Computer Vision Project {i}</h4>
                          <span className="rounded-full bg-emerald-100 px-2 py-0.5 text-xs text-emerald-700">
                            {i % 2 === 0 ? "Active" : "Recruiting"}
                          </span>
                        </div>
                        <p className="text-sm text-gray-500">Led by Jane Smith • 4/{i + 4} spots filled</p>
                      </div>
                      <Link to={`/projects/${i}`}>
                        <Button variant="outline" size="sm">
                          View Details
                        </Button>
                      </Link>
                    </div>
                  ))}
                </div>
              </CardContent>
              <CardFooter>
                <Button variant="outline" className="w-full">
                  View All Projects
                </Button>
              </CardFooter>
            </Card>
          </TabsContent>

          <TabsContent value="members" className="space-y-4">
            <Card>
              <CardHeader>
                <CardTitle>Member Management</CardTitle>
                <CardDescription>Review and manage club members</CardDescription>
              </CardHeader>
              <CardContent>
                <div className="space-y-4">
                  {[1, 2, 3, 4].map((i) => (
                    <div key={i} className="flex items-center justify-between border-b pb-4 last:border-0 last:pb-0">
                      <div className="flex items-center gap-4">
                        <div className="h-10 w-10 rounded-full bg-gray-200 flex items-center justify-center">
                          <span className="text-sm font-medium">{String.fromCharCode(64 + i)}S</span>
                        </div>
                        <div className="space-y-1">
                          <h4 className="font-medium">Alex Smith {i}</h4>
                          <div className="flex items-center gap-2">
                            <span className="rounded-full bg-gray-100 px-2 py-0.5 text-xs">
                              {i % 3 === 0 ? "General Member" : i % 3 === 1 ? "Project Member" : "Project Lead"}
                            </span>
                            <span className="text-xs text-gray-500">Joined Fall 2024</span>
                          </div>
                        </div>
                      </div>
                      <Link to={`/members/${i}`}>
                        <Button variant="outline" size="sm">
                          View Profile
                        </Button>
                      </Link>
                    </div>
                  ))}
                </div>
              </CardContent>
              <CardFooter>
                <Button variant="outline" className="w-full">
                  View All Members
                </Button>
              </CardFooter>
            </Card>
          </TabsContent>

          <TabsContent value="semesters" className="space-y-4">
            <Card>
              <CardHeader>
                <CardTitle>Semester Management</CardTitle>
                <CardDescription>Manage academic semesters and associated projects</CardDescription>
              </CardHeader>
              <CardContent>
                <div className="space-y-4">
                  {["Spring 2025", "Fall 2024", "Spring 2024", "Fall 2023"].map((semester, i) => (
                    <div key={i} className="flex items-center justify-between border-b pb-4 last:border-0 last:pb-0">
                      <div className="space-y-1">
                        <h4 className="font-medium">{semester}</h4>
                        <p className="text-sm text-gray-500">
                          {5 - i} projects • {20 - i * 3} members
                        </p>
                      </div>
                      <Link to={`/dashboard/semesters/${i}`}>
                        <Button variant="outline" size="sm">
                          Manage
                        </Button>
                      </Link>
                    </div>
                  ))}
                </div>
              </CardContent>
              <CardFooter className="flex justify-between">
                <Button variant="outline">View Archive</Button>
                <Button className="bg-emerald-600 hover:bg-emerald-700">
                  <PlusCircle className="mr-2 h-4 w-4" />
                  New Semester
                </Button>
              </CardFooter>
            </Card>
          </TabsContent>
        </Tabs>
      </div>
    </DashboardLayout>
  );
}

export default DashboardPage;
