import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from '../components/ui/button';
import { ArrowRight, Users, FolderKanban, Calendar } from 'lucide-react';

function HomePage() {
  return (
    <div>
      <section className="w-full py-12 md:py-24 lg:py-32 bg-gradient-to-b from-white to-gray-50">
        <div className="container px-4 md:px-6">
          <div className="grid gap-6 lg:grid-cols-2 lg:gap-12 items-center">
            <div className="space-y-4">
              <h1 className="text-3xl font-bold tracking-tighter sm:text-4xl md:text-5xl">
                ML@SJSU Project Management System
              </h1>
              <p className="text-gray-500 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed">
                Streamline project management, member eligibility, and project sign-ups for ML@SJSU. Showcase past and
                current projects while automating member validation.
              </p>
              <div className="flex flex-col gap-2 min-[400px]:flex-row">
                <Link to="/projects">
                  <Button className="bg-emerald-600 hover:bg-emerald-700">
                    Browse Projects
                    <ArrowRight className="ml-2 h-4 w-4" />
                  </Button>
                </Link>
                <Link to="/signup">
                  <Button variant="outline">Join as a Member</Button>
                </Link>
              </div>
            </div>
            <div className="flex justify-center">
              <img
                src="/placeholder.svg?height=400&width=600"
                alt="ML@SJSU Project Dashboard"
                className="rounded-lg object-cover border shadow-lg"
                width={600}
                height={400}
              />
            </div>
          </div>
        </div>
      </section>

      <section className="w-full py-12 md:py-24 bg-white">
        <div className="container px-4 md:px-6">
          <div className="flex flex-col items-center justify-center space-y-4 text-center">
            <div className="space-y-2">
              <h2 className="text-3xl font-bold tracking-tighter sm:text-4xl">Key Features</h2>
              <p className="max-w-[900px] text-gray-500 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed">
                Our platform offers a comprehensive solution for ML@SJSU's project management needs.
              </p>
            </div>
          </div>
          <div className="mx-auto grid max-w-5xl items-center gap-6 py-12 md:grid-cols-2 lg:grid-cols-3">
            <div className="flex flex-col items-center space-y-2 border rounded-lg p-6 shadow-sm">
              <Users className="h-12 w-12 text-emerald-600" />
              <h3 className="text-xl font-bold">Member Management</h3>
              <p className="text-sm text-gray-500 text-center">
                Automated validation of general and project member eligibility based on criteria.
              </p>
            </div>
            <div className="flex flex-col items-center space-y-2 border rounded-lg p-6 shadow-sm">
              <FolderKanban className="h-12 w-12 text-emerald-600" />
              <h3 className="text-xl font-bold">Project Tracking</h3>
              <p className="text-sm text-gray-500 text-center">
                Create, update, and manage projects with detailed information and member assignments.
              </p>
            </div>
            <div className="flex flex-col items-center space-y-2 border rounded-lg p-6 shadow-sm">
              <Calendar className="h-12 w-12 text-emerald-600" />
              <h3 className="text-xl font-bold">Semester Organization</h3>
              <p className="text-sm text-gray-500 text-center">
                Organize projects by semester and track progress throughout the academic year.
              </p>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default HomePage;
